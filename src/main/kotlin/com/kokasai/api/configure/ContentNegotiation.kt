package com.kokasai.api.configure

import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json

fun ContentNegotiation.Configuration.configureSerialization() {
    json()
}
