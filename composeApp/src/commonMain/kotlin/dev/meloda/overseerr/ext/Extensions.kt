package dev.meloda.overseerr.ext

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun <T> MutableStateFlow<T>.setValue(function: (T) -> T) {
    val newValue = function(value)
    update { newValue }
}
