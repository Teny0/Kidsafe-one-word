package com.example.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CategoryItem(
    val title: String,
    val icon: ImageVector,
    val sampleWord: String
)

val KNOWLEDGE_CATEGORIES = listOf(
    CategoryItem("Nutrition", Icons.Default.Restaurant, "choking"),
    CategoryItem("Sleep", Icons.Default.Bedtime, "sleep"),
    CategoryItem("Hygiene", Icons.Default.Bathtub, "bath"),
    CategoryItem("Home Safety", Icons.Default.Home, "burns"),
    CategoryItem("Medicine", Icons.Default.Medication, "medicine"),
    CategoryItem("Outdoor", Icons.Default.Park, "car"),
    CategoryItem("Digital", Icons.Default.Devices, "screen"),
    CategoryItem("Emotional", Icons.Default.EmojiEmotions, "tantrum")
)

@Composable
fun CategoryChips(
    selectedCategory: String?,
    onSelectCategory: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KNOWLEDGE_CATEGORIES.forEach { category ->
            val isSelected = selectedCategory == category.title
            FilterChip(
                selected = isSelected,
                onClick = { onSelectCategory(category) },
                label = {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.testTag("category_chip_${category.title.lowercase().replace(" ", "_")}")
            )
        }
    }
}
