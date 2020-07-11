package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import com.gitlab.nitgc.kokasai.the23rd.constants.SessionConstants
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.sessions.sessions

fun Routing.logoutRoute() {
    get(CommonRoutes.LOGOUT) {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(CommonRoutes.LOGIN)
    }
}