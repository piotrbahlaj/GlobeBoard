package com.piotrbahlaj.globeboard.common.countries.domain.repository

import com.piotrbahlaj.globeboard.common.countries.data.models.Country

interface CountryRepository {
    suspend fun getAllCountries(): Result<List<Country>>
    suspend fun getCountryByCode(alphaCode: String): Result<Country>
    suspend fun getCountriesByRegion(region: String): Result<List<Country>>
}