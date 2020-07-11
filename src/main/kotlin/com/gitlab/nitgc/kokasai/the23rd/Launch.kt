package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.routes.loginRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.logoutRoute
import com.gitlab.nitgc.kokasai.the23rd.routes.profileRoute
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.response.respondRedirect
import io.ktor.routing.routing
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.launch() {
    install(Sessions) {
        configureAuthCookie()
    }

    install(Authentication) {
        configureFormAuth()
        configureSessionAuth()
    }

    routing {
        loginRoute()
        logoutRoute()
        profileRoute()
    }
}

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserIdPrincipal>(CookiesConstants.AUTH, storage = SessionStorageMemory()) {
        cookie.path = "/"
    }
}

fun Authentication.Configuration.configureFormAuth() {
    form(AuthName.FORM) {
        userParamName = AuthFormFields.USERNAME
        passwordParamName = AuthFormFields.PASSWORD
        challenge {
            val failures = call.authentication.allFailures
            call.respondRedirect(
                    when (failures.singleOrNull()) {
                        AuthenticationFailedCause.InvalidCredentials -> {
                            "${CommonRoutes.LOGIN}?invalid"
                        }
                        AuthenticationFailedCause.NoCredentials -> {
                            "${CommonRoutes.LOGIN}?no"
                        }
                        else -> {
                            CommonRoutes.LOGIN
                        }
                    }
            )
        }
        validate {
            if (it.name == AuthTestLogin.USERNAME && it.password == AuthTestLogin.PASSWORD) {
               UserIdPrincipal(it.name)
            } else {
                null
            }
        }
    }
}

fun Authentication.Configuration.configureSessionAuth() {
    session<UserIdPrincipal>(AuthName.SESSION) {
        challenge {
            call.respondRedirect("${CommonRoutes.LOGIN}?no")
        }
        validate { session ->
            session
        }
    }
}