package com.kokasai.api.routes.http.bus

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.response.respond

object BusTokenManager {
    private val busList = mapOf(
        "abcdef" to "abc" // TODO 仮のトークン確認
    )

    suspend inline fun challenge(call: ApplicationCall, onSuccess: (String) -> Unit) {
        find(call)?.let(onSuccess) ?: run {
            call.respond(HttpStatusCode.Forbidden, "null")
        }
    }

    fun find(call: ApplicationCall): String? {
        return find(call.request.header("token"))
    }

    private fun find(token: String?): String? {
        return busList[token]
    }
}
