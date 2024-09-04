package com.diamood.api.country

import com.diamood.data.login.Country

interface CountryRepository {
    fun getCountryList(): List<Country>
}
