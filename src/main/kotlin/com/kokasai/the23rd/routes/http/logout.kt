package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.flowerkt.route.respondRedirect
import com.kokasai.the23rd.constants.Auth
import io.ktor.application.call
import io.ktor.sessions.sessions

val logout = buildGetRoute {
    call.sessions.clear(Auth.UserLogin.cookie)
    call.respondRedirect(HttpRoute.Login)
}
