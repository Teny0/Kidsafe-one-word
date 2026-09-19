package com.example.data.repository

import com.example.data.local.SafetyDao
import com.example.data.local.SafetyEntity
import com.example.data.model.SafetyGuide
import com.example.data.parser.SafetyGuideParser
import com.example.data.preloaded.PreloadedGuides
import com.example.data.remote.GeminiSafetyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SafetyRepository(
    private val safetyDao: SafetyDao,
    private val geminiService: GeminiSafetyService = GeminiSafetyService()
) {

    val favoritesFlow: Flow<List<SafetyGuide>> = safetyDao.getAllFavorites().map { entities ->
        entities.map { entity ->
            SafetyGuideParser.parse(
                word = entity.word,
                rawText = entity.rawContent,
                isFavorite = true
            )
        }
    }

    val historyFlow: Flow<List<SafetyGuide>> = safetyDao.getAllHistory().map { entities ->
        entities.map { entity ->
            SafetyGuideParser.parse(
                word = entity.word,
                rawText = entity.rawContent,
                isFavorite = entity.isFavorite
            )
        }
    }

    suspend fun getSafetyGuide(word: String): Result<SafetyGuide> {
        val cleanedWord = word.trim().uppercase()
        if (cleanedWord.isBlank()) {
            return Result.failure(IllegalArgumentException("Please enter a word."))
        }

        // 1. Check local database first for saved/cached version
        val cached = safetyDao.getGuide(cleanedWord)
        val isFav = cached?.isFavorite ?: false

        // 2. Check preloaded evidence-informed guides (instant 0ms)
        val preloaded = PreloadedGuides.getGuide(cleanedWord)
        if (preloaded != null) {
            val guide = preloaded.copy(isFavorite = isFav)
            // Save to recent search history
            safetyDao.insertOrUpdate(
                SafetyEntity(
                    word = guide.word,
                    rawContent = guide.rawText,
                    category = guide.category,
                    isFavorite = isFav,
                    timestamp = System.currentTimeMillis()
                )
            )
            return Result.success(guide)
        }

        // 3. If cached previously from AI
        if (cached != null && cached.rawContent.isNotBlank()) {
            val guide = SafetyGuideParser.parse(
                word = cached.word,
                rawText = cached.rawContent,
                isFavorite = cached.isFavorite,
                isCustomAi = true
            )
            // Update timestamp
            safetyDao.insertOrUpdate(cached.copy(timestamp = System.currentTimeMillis()))
            return Result.success(guide)
        }

        // 4. Request from Gemini AI service
        val aiResult = geminiService.generateGuide(cleanedWord)
        return aiResult.onSuccess { guide ->
            safetyDao.insertOrUpdate(
                SafetyEntity(
                    word = guide.word,
                    rawContent = guide.rawText,
                    category = guide.category,
                    isFavorite = isFav,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    suspend fun toggleFavorite(guide: SafetyGuide): Boolean {
        val newStatus = !guide.isFavorite
        safetyDao.insertOrUpdate(
            SafetyEntity(
                word = guide.word,
                rawContent = guide.rawText,
                category = guide.category,
                isFavorite = newStatus,
                timestamp = System.currentTimeMillis()
            )
        )
        return newStatus
    }

    suspend fun clearHistory() {
        safetyDao.clearNonFavorites()
    }
}
