package com.kokasai.api.http

import com.kokasai.api.auth.OnetimePasswordManager
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val id: String)

val login: RouteAction = {
    post {
        try {
            val loginRequest = call.receive<LoginRequest>()
            when {
                loginRequest.id.matches("^[A-Za-z][A-Za-z0-9.]*$".toRegex()).not() -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
                OnetimePasswordManager.generate(loginRequest) -> {
                    call.respond(HttpStatusCode.OK)
                }
                else -> {
                    call.respond(HttpStatusCode.TooManyRequests)
                }
            }
        } catch (ex: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
