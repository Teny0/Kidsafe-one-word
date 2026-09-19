package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.SafetyDatabase
import com.example.data.model.SafetyGuide
import com.example.data.preloaded.PreloadedGuides
import com.example.data.repository.SafetyRepository
import com.example.tts.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SafetyUiState(
    val searchQuery: String = "",
    val currentGuide: SafetyGuide? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedCategory: String? = null,
    val showReferenceSheet: Boolean = false,
    val showEmergencySheet: Boolean = false,
    val showFavoritesSheet: Boolean = false,
    val isActionCompleted: Boolean = false,
    val multiWordTip: String? = null
)

class SafetyViewModel(application: Application) : AndroidViewModel(application) {

    private val database = SafetyDatabase.getDatabase(application)
    private val repository = SafetyRepository(database.safetyDao())
    val ttsManager = TtsManager(application)

    private val _uiState = MutableStateFlow(SafetyUiState())
    val uiState: StateFlow<SafetyUiState> = _uiState.asStateFlow()

    val favorites: StateFlow<List<SafetyGuide>> = repository.favoritesFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val history: StateFlow<List<SafetyGuide>> = repository.historyFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Load default welcome guide ("SLEEP" or "CHOKING") on launch for instant guidance
        search("sleep")
    }

    fun onQueryChange(query: String) {
        val trimmed = query.trim()
        val words = trimmed.split(Regex("\\s+")).filter { it.isNotBlank() }

        val tip = if (words.size > 1) {
            "Tip: KidSafe OneWord works best with a single word like '${words.last()}'"
        } else {
            null
        }

        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            multiWordTip = tip,
            errorMessage = null
        )
    }

    fun search(overrideWord: String? = null) {
        val rawInput = overrideWord ?: _uiState.value.searchQuery
        val words = rawInput.trim().split(Regex("\\s+")).filter { it.isNotBlank() }

        if (words.isEmpty()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Please enter one word (e.g. fever, water, sleep)."
            )
            return
        }

        // Take the first or most prominent word if multiple were entered
        val targetWord = if (overrideWord != null) overrideWord.trim() else words.first()

        _uiState.value = _uiState.value.copy(
            searchQuery = targetWord,
            isLoading = true,
            errorMessage = null,
            multiWordTip = null,
            isActionCompleted = false
        )

        // Stop TTS if previous guide was speaking
        ttsManager.stop()

        viewModelScope.launch {
            val result = repository.getSafetyGuide(targetWord)
            result.onSuccess { guide ->
                _uiState.value = _uiState.value.copy(
                    currentGuide = guide,
                    isLoading = false,
                    errorMessage = null
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Unable to load safety guide. Please try again."
                )
            }
        }
    }

    fun toggleFavorite() {
        val guide = _uiState.value.currentGuide ?: return
        viewModelScope.launch {
            val newStatus = repository.toggleFavorite(guide)
            _uiState.value = _uiState.value.copy(
                currentGuide = guide.copy(isFavorite = newStatus)
            )
        }
    }

    fun toggleSpeech() {
        val guide = _uiState.value.currentGuide ?: return
        if (ttsManager.isSpeaking.value) {
            ttsManager.stop()
        } else {
            ttsManager.speak(guide.toFormattedSpeech())
        }
    }

    fun selectCategory(category: String?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun toggleActionCompleted() {
        _uiState.value = _uiState.value.copy(isActionCompleted = !_uiState.value.isActionCompleted)
    }

    fun showReferenceSheet(show: Boolean) {
        _uiState.value = _uiState.value.copy(showReferenceSheet = show)
    }

    fun showEmergencySheet(show: Boolean) {
        _uiState.value = _uiState.value.copy(showEmergencySheet = show)
    }

    fun showFavoritesSheet(show: Boolean) {
        _uiState.value = _uiState.value.copy(showFavoritesSheet = show)
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}
