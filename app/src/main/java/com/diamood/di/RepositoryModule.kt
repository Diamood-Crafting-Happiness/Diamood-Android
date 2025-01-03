package com.diamood.di

import com.diamood.api.country.CountryRepository
import com.diamood.api.country.CountryRepositoryImpl
import com.diamood.api.home.HomeRepository
import com.diamood.api.home.HomeRepositoryImpl
import com.diamood.api.json.JsonHelper
import com.diamood.api.json.JsonHelperIml
import com.diamood.api.login.LoginRepository
import com.diamood.api.login.LoginRepositoryImpl
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
    abstract fun bindLoginRepository(
        loginRepository: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindJsonHelper(
        jsonHelperIml: JsonHelperIml
    ): JsonHelper
}
