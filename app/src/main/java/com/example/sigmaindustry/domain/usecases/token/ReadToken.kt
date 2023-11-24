package com.example.sigmaindustry.domain.usecases.token

import com.example.sigmaindustry.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private var isTokenPresent = false
class ReadToken @Inject constructor(
    private val localUserManger: LocalUserManger,
) {

    suspend operator fun invoke(): String? {
        val token = localUserManger.readToken()
        isTokenPresent = token != null
        return token
    }

    fun getTokenPresent(): Boolean {
        return isTokenPresent
    }
}