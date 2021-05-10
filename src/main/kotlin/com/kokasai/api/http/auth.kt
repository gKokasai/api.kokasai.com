package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions

val auth: RouteAction = {
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
