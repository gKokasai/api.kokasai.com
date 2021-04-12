package com.kokasai.api.configure

import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson

fun ContentNegotiation.Configuration.configureGson() {
    gson()
}
