package com.diamood.api.country

import com.diamood.api.json.JsonHelper
import com.diamood.data.login.Country
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val helper: JsonHelper
) : CountryRepository {

    override suspend fun getCountryList(): List<Country> {
        return helper.getAsset("Country.json")
    }
}

class CountryRepositoryFakeImpl : CountryRepository {

    var fakeCountryList = listOf(
        Country("España", "🇪🇸", "ES", "+34"),
        Country("España", "🇪🇸", "ES", "+34"),
        Country("España", "🇪🇸", "ES", "+34"),
        Country("España", "🇪🇸", "ES", "+34"),
        Country("España", "🇪🇸", "ES", "+34")
    )

    override suspend fun getCountryList(): List<Country> {
        return fakeCountryList
    }
}
