package com.diamood.data.login

data class PhoneInfo(
    val country: Country,
    val number: String
)

data class Country(
    val name: String?,
    val flag: String?,
    val code: String?,
    val phoneCode: String?,
)
