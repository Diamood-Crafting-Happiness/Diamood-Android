package com.diamood.api.login

interface LoginRepository {
    suspend fun sendSMS(number: String)
    suspend fun postLogin(smsCode: String)
}
