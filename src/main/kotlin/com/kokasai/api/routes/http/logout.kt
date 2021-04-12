package com.kokasai.api.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.flowerkt.route.respondRedirect
import com.kokasai.api.auth.UserLogin
import io.ktor.application.call
import io.ktor.routing.post
import io.ktor.sessions.sessions

val logout: RouteAction = {
    post {
        call.sessions.clear(UserLogin.cookie)
        call.respondRedirect(HttpRoute.Login)
    }
}
