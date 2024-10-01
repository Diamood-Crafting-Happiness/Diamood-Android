package com.diamood.di

import com.diamood.api.country.CountryRepository
import com.diamood.api.country.CountryRepositoryImpl
import com.diamood.api.json.JsonHelper
import com.diamood.api.json.JsonHelperIml
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    @Binds
    abstract fun bindJsonHelper(
        jsonHelperIml: JsonHelperIml
    ): JsonHelper
}
