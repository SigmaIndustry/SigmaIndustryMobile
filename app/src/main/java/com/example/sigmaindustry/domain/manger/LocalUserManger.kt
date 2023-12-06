package com.example.sigmaindustry.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveAppEntry()
    suspend fun saveToken(token: String?)
    suspend fun readToken(): String?
    fun readAppEntry(): Flow<Boolean>
}