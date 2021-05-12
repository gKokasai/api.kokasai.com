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
data class PostLoginRequest(val id: String)

val login: RouteAction = {
    post {
        try {
            val request = call.receive<PostLoginRequest>()
            when {
                request.id.matches("^[A-Za-z][A-Za-z0-9.]*$".toRegex()).not() -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
                OnetimePasswordManager.generate(request.id) -> {
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
