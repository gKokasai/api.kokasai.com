package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.buildRoute
import com.kokasai.the23rd.auth.UserLogin
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions

val login = buildRoute {
    get {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
    authenticate(UserLogin.authName) {
        post {
            val principal = call.principal<UserLogin.Data>()
            call.sessions.set(UserLogin.cookie, principal)
            call.respond(HttpStatusCode.OK)
        }
    }
}
