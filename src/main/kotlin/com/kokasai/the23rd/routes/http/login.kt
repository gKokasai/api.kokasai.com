package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.the23rd.auth.OnetimePasswordManager
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post

data class LoginRequest(val id: String)

val login: RouteAction = {
    post {
        try {
            val loginRequest = call.receive<LoginRequest>()
            OnetimePasswordManager.generate(loginRequest)
            call.respond(HttpStatusCode.OK)
        } catch (ex: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
