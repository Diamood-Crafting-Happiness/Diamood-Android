package com.diamood.data.login

import kotlinx.serialization.Serializable

data class PhoneInfo(
    val country: Country,
    val number: String
)

@Serializable
data class Country(
    val name: String?,
    val flag: String?,
    val code: String?,
    val phoneCode: String?,
) {
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf("$name", "$code", "$phoneCode")

        return matchingCombinations.any { combination ->
            combination.lowercase().contains(query.lowercase())
        }
    }
}
