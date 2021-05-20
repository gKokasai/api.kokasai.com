package com.kokasai.api

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.auth.UserLogin
import io.ktor.application.call
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.features.toLogString
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.contentType
import io.ktor.request.httpMethod
import io.ktor.request.uri
import io.ktor.response.respond
import io.ktor.serialization.json
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

fun ContentNegotiation.Configuration.configureSerialization() {
    json()
}

fun CORS.Configuration.configureCORS() {
    anyHost()
    header(HttpHeaders.Authorization)
    header(HttpHeaders.ContentType)
    header(UserLogin.sessionHeader)
    header("withCredentials")
    exposeHeader(UserLogin.sessionHeader)
    host("kokasai.com", schemes = listOf("http", "https"), subDomains = listOf("panel"))
    allowCredentials = true
}

fun StatusPages.Configuration.configureStatusPages() {
    exception<Throwable> { ex ->
        val request = call.request
        api.logger.error(
            buildString {
                appendLine("Unhandled Exception")
                appendLine("URI: ${request.httpMethod.value} ${request.uri}")
                appendLine("Content-Type: ${request.contentType()}")
                appendLine("Header: ${request.headers.entries().joinToString("; ") { it.key + "=" + it.value }}")
                request.cookies.rawCookies.takeUnless(Map<*, *>::isEmpty)?.let {
                    appendLine("Cookie: ${it.entries.joinToString()}")
                }
                request.queryParameters.takeUnless(Parameters::isEmpty)?.let {
                    appendLine("Parameter: ${it.entries().joinToString()}")
                }
                append("Exception: ${ex.stackTraceToString()}")
            }
        )
        call.respond(HttpStatusCode.InternalServerError)
    }
}
