package com.piotrbahlaj.globeboard.core.di

import com.piotrbahlaj.globeboard.common.countries.data.repository.CountryRepositoryImpl
import com.piotrbahlaj.globeboard.common.countries.data.service.CountryApiService
import com.piotrbahlaj.globeboard.common.countries.domain.repository.CountryRepository
import com.piotrbahlaj.globeboard.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single { CountryApiService(get()) }
    single<CountryRepository> { CountryRepositoryImpl(get()) }
}