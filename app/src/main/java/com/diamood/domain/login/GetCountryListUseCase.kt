package com.diamood.domain.login

import com.diamood.api.country.CountryRepository
import com.diamood.data.login.Country
import javax.inject.Inject

class GetCountryListUseCase @Inject constructor(
    private val repository: CountryRepository
) {

    suspend operator fun invoke(): List<Country> {
        return repository.getCountryList()
    }
}
