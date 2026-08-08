package com.piotrbahlaj.globeboard

import androidx.compose.ui.window.ComposeUIViewController
import com.piotrbahlaj.globeboard.core.di.initKoin

fun MainViewController() = ComposeUIViewController {
    App()
}

fun doInitKoin() {
    initKoin()
}