package com.piotrbahlaj.globeboard.core.di

import com.piotrbahlaj.globeboard.features.explorer.presentation.ExplorerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ExplorerViewModel(get()) }
}