package com.example.sigmaindustry.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sigmaindustry.data.remote.dto.SearchResult
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: SearchResult)

    @Delete
    suspend fun delete(article: SearchResult)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<SearchResult>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): SearchResult?

}