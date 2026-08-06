package com.piotrbahlaj.globeboard

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform