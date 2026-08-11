package com.piotrbahlaj.globeboard.core.utilities

fun formatPopulation(population: Long): String {
    return when {
        population >= 1_000_000_000 -> "${population / 1_000_000_000}B"
        population >= 1_000_000 -> "${population / 1_000_000}M"
        else -> population.toString()
    }
}