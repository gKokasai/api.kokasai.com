package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.the23rd.configure.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.websocket.*

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
