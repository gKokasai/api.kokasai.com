package com.kokasai.api.configure

import com.kokasai.api.KokasaiApi
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.request.contentType
import io.ktor.request.httpMethod
import io.ktor.request.uri
import io.ktor.response.respond
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

fun StatusPages.Configuration.configureStatusPages() {
    exception<Throwable> { ex ->
        val request = call.request
        api.logger.error(
            buildString {
                appendLine("Unhandled Exception")
                appendLine("URI: ${request.httpMethod.value} ${request.uri}")
                appendLine("Content-Type: ${request.contentType()}")
                appendLine("Header: ${request.headers.entries().joinEntriesToString()}")
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

private fun Set<Map.Entry<String, List<String>>>.joinEntriesToString() = joinToString("; ") { it.key + "=" + it.value }
