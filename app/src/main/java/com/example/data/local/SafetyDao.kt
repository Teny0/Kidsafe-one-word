package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SafetyDao {
    @Query("SELECT * FROM safety_guides ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<SafetyEntity>>

    @Query("SELECT * FROM safety_guides WHERE isFavorite = 1 ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<SafetyEntity>>

    @Query("SELECT * FROM safety_guides WHERE LOWER(word) = LOWER(:word) LIMIT 1")
    suspend fun getGuide(word: String): SafetyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(guide: SafetyEntity)

    @Query("UPDATE safety_guides SET isFavorite = :isFavorite WHERE LOWER(word) = LOWER(:word)")
    suspend fun setFavorite(word: String, isFavorite: Boolean)

    @Query("DELETE FROM safety_guides WHERE LOWER(word) = LOWER(:word)")
    suspend fun delete(word: String)

    @Query("DELETE FROM safety_guides WHERE isFavorite = 0")
    suspend fun clearNonFavorites()
}
