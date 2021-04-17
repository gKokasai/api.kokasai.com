package com.kokasai.api.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.api.auth.OnetimePasswordManager
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post

data class LoginRequest(val id: String) {
    val mailAddress = "$id@gunma.kosen-ac.jp"
}

val login: RouteAction = {
    post {
        try {
            val loginRequest = call.receive<LoginRequest>()
            if (OnetimePasswordManager.generate(loginRequest)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.TooManyRequests)
            }
        } catch (ex: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
