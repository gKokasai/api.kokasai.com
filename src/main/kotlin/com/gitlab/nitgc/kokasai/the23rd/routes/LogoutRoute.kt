package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.HtmlRoute
import com.gitlab.nitgc.kokasai.the23rd.constants.SessionConstants
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.sessions.sessions

fun Routing.logoutRoute() {
    get(HtmlRoute.Logout) {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(HtmlRoute.Login)
    }
}