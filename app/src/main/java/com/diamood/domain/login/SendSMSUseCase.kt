package com.diamood.domain.login

import com.diamood.api.login.LoginRepository
import com.diamood.domain.Result
import com.diamood.domain.Result.Success
import javax.inject.Inject

class SendSMSUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(number: String): Result {
        repository.sendSMS(number)
        return Success
    }
}
