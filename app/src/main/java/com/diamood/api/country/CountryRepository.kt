package com.diamood.api.country

import com.diamood.data.login.Country

interface CountryRepository {
    suspend fun getCountryList(): List<Country>
}
