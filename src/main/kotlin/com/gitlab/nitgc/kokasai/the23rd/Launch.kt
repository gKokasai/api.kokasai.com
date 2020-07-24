package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.the23rd.configure.configureAuthCookie
import com.gitlab.nitgc.kokasai.the23rd.configure.configureFormAuth
import com.gitlab.nitgc.kokasai.the23rd.configure.configureSessionAuth
import com.gitlab.nitgc.kokasai.the23rd.routes.accountRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.cssRoutes
import com.gitlab.nitgc.kokasai.the23rd.routes.homeRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.jsRoutes
import com.gitlab.nitgc.kokasai.the23rd.routes.loginRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.logoutRoute
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.routing.routing
import io.ktor.sessions.Sessions

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.launch() {
    install(Sessions) {
        configureAuthCookie()
    }

    install(Authentication) {
        configureFormAuth()
        configureSessionAuth()
    }

    routing {
        homeRoute()
        loginRoute()
        logoutRoute()
        accountRoute()
        cssRoutes()
        jsRoutes()
    }
}
