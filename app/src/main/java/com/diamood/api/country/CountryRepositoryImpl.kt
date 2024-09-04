package com.diamood.api.country

import com.diamood.data.login.Country
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor() : CountryRepository {
    override fun getCountryList(): List<Country> {
        return emptyList()
    }
}

class CountryRepositoryFakeImpl : CountryRepository {
    override fun getCountryList(): List<Country> {
        return listOf(
            Country("España", "🇪🇸", "ES", "+34"),
            Country("España", "🇪🇸", "ES", "+34"),
            Country("España", "🇪🇸", "ES", "+34"),
            Country("España", "🇪🇸", "ES", "+34"),
            Country("España", "🇪🇸", "ES", "+34")
        )
    }
}