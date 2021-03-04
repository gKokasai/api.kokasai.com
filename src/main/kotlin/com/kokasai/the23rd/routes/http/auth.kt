package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.the23rd.auth.OnetimePasswordManager
import com.kokasai.the23rd.auth.UserLogin
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.sessions.sessions

data class AuthRequest(val id: String, val pass: String)

val auth: RouteAction = {
    post {
        try {
            val authRequest = call.receive<AuthRequest>()
            when (OnetimePasswordManager.auth(authRequest)) {
                OnetimePasswordManager.AuthResult.Success -> {
                    call.sessions.set(UserLogin.cookie, UserLogin.Data(authRequest.id))
                    HttpStatusCode.OK
                }
                OnetimePasswordManager.AuthResult.TooManyRequest -> HttpStatusCode.TooManyRequests
                OnetimePasswordManager.AuthResult.Error -> HttpStatusCode.Unauthorized
            }.let { call.respond(it) }
        } catch (ex: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
