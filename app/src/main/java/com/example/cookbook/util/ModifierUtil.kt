package com.example.cookbook.util

import androidx.compose.ui.Modifier

fun Modifier.applyIf(condition: Boolean, modifier: Modifier): Modifier {
    return if(condition) {
        then(modifier)
    } else {
        this
    }
}