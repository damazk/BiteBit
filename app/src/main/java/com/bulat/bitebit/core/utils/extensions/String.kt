package com.bulat.bitebit.core.utils.extensions

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

fun String.capitalizeEachWord(locale: Locale): String {
    val words = split(' ')
    return words.map {
        it.capitalize(locale)
    }.joinToString(separator = " ")
}