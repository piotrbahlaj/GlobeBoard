package com.piotrbahlaj.globeboard.core.utilities

fun formatPopulation(population: Long, abbreviated: Boolean = true): String {
    if (!abbreviated) {
        return formatWithThousandsSeparator(population)
    }

    return when {
        population >= 1_000_000_000 -> "${roundToTwoDecimals(population / 1_000_000_000.0)}B"
        population >= 1_000_000 -> "${roundToTwoDecimals(population / 1_000_000.0)}M"
        else -> formatWithThousandsSeparator(population)
    }
}

private fun formatWithThousandsSeparator(number: Long): String {
    val text = number.toString()
    val builder = StringBuilder()
    for (i in text.indices) {
        if (i > 0 && (text.length - i) % 3 == 0) {
            builder.append(",")
        }
        builder.append(text[i])
    }
    return builder.toString()
}

private fun roundToTwoDecimals(value: Double): Double {
    return kotlin.math.round(value * 100) / 100
}