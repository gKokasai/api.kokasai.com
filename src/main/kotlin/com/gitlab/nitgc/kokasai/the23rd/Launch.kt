package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.the23rd.configure.configureAuthCookie
import com.gitlab.nitgc.kokasai.the23rd.configure.configureFormAuth
import com.gitlab.nitgc.kokasai.the23rd.configure.configureGson
import com.gitlab.nitgc.kokasai.the23rd.configure.configureSessionAuth
import com.gitlab.nitgc.kokasai.the23rd.routes.accessRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.accountRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.apiRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.cssRoutes
import com.gitlab.nitgc.kokasai.the23rd.routes.homeRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.loginRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.logoutRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.staticRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.testRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.webSocketRoute
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.sessions.Sessions
import io.ktor.websocket.WebSockets

@Suppress("unused") // Referenced in application.conf
fun Application.launch() {
    install(Sessions) {
        configureAuthCookie()
    }

    install(Authentication) {
        configureFormAuth()
        configureSessionAuth()
    }

    install(ContentNegotiation) {
        configureGson()
    }

    install(WebSockets)

    routing {
        homeRoute()
        loginRoute()
        logoutRoute()
        accountRoute()
        accessRoute()
        cssRoutes()
        staticRoute()
        apiRoute()
        webSocketRoute()

        testRoute()
    }
}
