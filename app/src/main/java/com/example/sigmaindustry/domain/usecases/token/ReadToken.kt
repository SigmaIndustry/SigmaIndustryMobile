package com.example.sigmaindustry.domain.usecases.token

import com.example.sigmaindustry.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReadToken @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(): String? {
       return  localUserManger.readToken()
    }

}