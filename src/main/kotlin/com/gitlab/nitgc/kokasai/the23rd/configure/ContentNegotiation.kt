package com.gitlab.nitgc.kokasai.the23rd.configure

import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson

fun ContentNegotiation.Configuration.configureGson() {
    gson()
}