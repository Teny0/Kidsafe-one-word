package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "safety_guides")
data class SafetyEntity(
    @PrimaryKey
    val word: String,
    val rawContent: String,
    val category: String,
    val isFavorite: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
