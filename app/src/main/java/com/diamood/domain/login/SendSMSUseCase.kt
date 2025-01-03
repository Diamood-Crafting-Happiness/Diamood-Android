package com.diamood.domain.login

import com.diamood.api.login.LoginRepository
import com.diamood.domain.login.Result.Success
import javax.inject.Inject

sealed class Result {
    object Success : Result()
    object Failure : Result()
}

class SendSMSUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(number: String): Result {
        repository.sendSMS(number)
        return Success
    }
}
