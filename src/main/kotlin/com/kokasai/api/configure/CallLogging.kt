package com.kokasai.api.configure

import io.ktor.features.CallLogging
import io.ktor.features.toLogString
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

fun CallLogging.Configuration.configureCallLogging() {
    level = Level.INFO
    logger = LoggerFactory.getLogger("Route")
    format { call ->
        val requestLog = call.request.toLogString()
        when (val status = call.response.status() ?: "Unhandled") {
            HttpStatusCode.Found -> "$status: $requestLog -> ${call.response.headers[HttpHeaders.Location]}"
            else -> "$status: $requestLog"
        }
    }
}
