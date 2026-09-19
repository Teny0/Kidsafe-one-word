package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.preloaded.PreloadedGuides
import com.example.ui.components.CategoryChips
import com.example.ui.components.EmergencyHelpSheet
import com.example.ui.components.FavoritesSheet
import com.example.ui.components.OneWordSearchBar
import com.example.ui.components.QuickWordChips
import com.example.ui.components.ReferenceValuesSheet
import com.example.ui.components.SafetyGuideView
import com.example.ui.components.SafetyTopBar
import com.example.ui.theme.AlertRed
import com.example.ui.theme.AlertRedContainer

@Composable
fun SafetyScreen(
    viewModel: SafetyViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val history by viewModel.history.collectAsStateWithLifecycle()
    val isSpeaking by viewModel.ttsManager.isSpeaking.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SafetyTopBar(
                favoriteCount = favorites.size,
                onOpenFavorites = { viewModel.showFavoritesSheet(true) },
                onOpenReference = { viewModel.showReferenceSheet(true) },
                onOpenEmergency = { viewModel.showEmergencySheet(true) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // 1. Search Bar with Single-Word focus
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    OneWordSearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = { viewModel.onQueryChange(it) },
                        onSearch = { viewModel.search() },
                        isLoading = uiState.isLoading,
                        multiWordTip = uiState.multiWordTip
                    )
                }
            }

            // 2. Knowledge Category Filter Chips
            item {
                CategoryChips(
                    selectedCategory = uiState.selectedCategory,
                    onSelectCategory = { category ->
                        viewModel.selectCategory(category.title)
                        viewModel.search(category.sampleWord)
                    }
                )
            }

            // 3. Quick Suggested Words
            item {
                val wordsToShow = if (uiState.selectedCategory != null) {
                    PreloadedGuides.categorizedWords[uiState.selectedCategory] ?: PreloadedGuides.suggestedWords
                } else {
                    PreloadedGuides.suggestedWords
                }

                QuickWordChips(
                    words = wordsToShow,
                    currentWord = uiState.currentGuide?.word,
                    onWordClick = { word ->
                        viewModel.search(word)
                    }
                )
            }

            // 4. Loading State
            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(44.dp),
                                color = MaterialTheme.colorScheme.primary,
                                strokeWidth = 3.5.dp
                            )
                            Text(
                                text = "Preparing pediatrician-informed safety guide...",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 5. Error State
            if (uiState.errorMessage != null && !uiState.isLoading) {
                item {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = AlertRedContainer.copy(alpha = 0.5f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = AlertRed,
                                    modifier = Modifier.size(22.dp)
                                )
                                Text(
                                    text = "Notice",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = AlertRed
                                    )
                                )
                            }
                            Text(
                                text = uiState.errorMessage ?: "An error occurred.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Button(
                                onClick = { viewModel.search() },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.size(6.dp))
                                Text("Retry")
                            }
                        }
                    }
                }
            }

            // 6. Current Guide Content View
            uiState.currentGuide?.let { guide ->
                if (!uiState.isLoading) {
                    item {
                        SafetyGuideView(
                            guide = guide,
                            isSpeaking = isSpeaking,
                            isActionCompleted = uiState.isActionCompleted,
                            onToggleSpeech = { viewModel.toggleSpeech() },
                            onToggleFavorite = { viewModel.toggleFavorite() },
                            onToggleAction = { viewModel.toggleActionCompleted() },
                            onOpenEmergency = { viewModel.showEmergencySheet(true) }
                        )
                    }
                }
            }
        }
    }

    // Modal Sheets
    if (uiState.showReferenceSheet) {
        ReferenceValuesSheet(
            onDismiss = { viewModel.showReferenceSheet(false) }
        )
    }

    if (uiState.showEmergencySheet) {
        EmergencyHelpSheet(
            onDismiss = { viewModel.showEmergencySheet(false) }
        )
    }

    if (uiState.showFavoritesSheet) {
        FavoritesSheet(
            favorites = favorites,
            history = history,
            onSelectGuide = { word -> viewModel.search(word) },
            onClearHistory = { viewModel.clearHistory() },
            onDismiss = { viewModel.showFavoritesSheet(false) }
        )
    }
}
