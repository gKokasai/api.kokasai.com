package com.kokasai.the23rd.configure

import io.ktor.features.*
import io.ktor.gson.*

fun ContentNegotiation.Configuration.configureGson() {
    gson()
}