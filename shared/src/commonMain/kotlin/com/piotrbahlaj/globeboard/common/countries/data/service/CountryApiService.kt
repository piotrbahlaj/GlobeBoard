package com.piotrbahlaj.globeboard.common.countries.data.service

import com.piotrbahlaj.globeboard.common.countries.data.models.Country
import com.piotrbahlaj.globeboard.core.constants.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CountryApiService(
    private val httpClient: HttpClient
) {
    suspend fun getAllCountries(): List<Country> {
        return httpClient.get("${Constants.COUNTRIES_BASE_URL}/countries").body()
    }

    suspend fun getCountryByCode(alphaCode: String): Country {
        return httpClient.get("${Constants.COUNTRIES_BASE_URL}/alpha/$alphaCode").body()
    }

    suspend fun getCountriesByRegion(region: String): List<Country> {
        return httpClient.get("${Constants.COUNTRIES_BASE_URL}/region/$region").body()
    }
}