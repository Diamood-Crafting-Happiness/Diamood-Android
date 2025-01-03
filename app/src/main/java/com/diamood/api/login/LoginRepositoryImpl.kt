package com.diamood.api.login

import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun sendSMS(number: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun postLogin(smsCode: String) {
        //TODO("Not yet implemented")
    }
}

class LoginRepositoryImplFake : LoginRepository {

    var smsSent = false
    override suspend fun sendSMS(number: String) {
        delay(30L)
        smsSent = true
    }

    var loginPosted = false
    override suspend fun postLogin(smsCode: String) {
        loginPosted = true
    }
}

