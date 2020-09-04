package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Routing.logoutRoute() {
    get(HtmlRoute.Logout) {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(HtmlRoute.Login)
    }
}