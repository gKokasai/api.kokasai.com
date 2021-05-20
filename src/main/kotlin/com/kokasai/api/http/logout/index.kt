package com.kokasai.api.http.logout

import com.kokasai.api.auth.UserLogin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.sessions.sessions

val index: RouteAction = {
    post {
        call.sessions.clear(UserLogin.sessionHeader)
        call.respond(HttpStatusCode.OK)
    }
}
