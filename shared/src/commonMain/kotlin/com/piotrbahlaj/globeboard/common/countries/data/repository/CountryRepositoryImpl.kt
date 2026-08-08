package com.piotrbahlaj.globeboard.common.countries.data.repository

import com.piotrbahlaj.globeboard.common.countries.data.models.Country
import com.piotrbahlaj.globeboard.common.countries.data.service.CountryApiService
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val apiService: CountryApiService
) : CountryRepository {

    override suspend fun getAllCountries(): Result<List<Country>> {
        return try {
            Result.success(apiService.getAllCountries())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCountryByCode(alphaCode: String): Result<Country> {
        return try {
            Result.success(apiService.getCountryByCode(alphaCode))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCountriesByRegion(region: String): Result<List<Country>> {
        return try {
            Result.success(apiService.getCountriesByRegion(region))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}