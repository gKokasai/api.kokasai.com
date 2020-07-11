package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import io.ktor.application.call
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.html.respondHtml
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div

fun Route.profileRoute() {
    authenticate(AuthName.SESSION) {
        get(CommonRoutes.PROFILE) {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondHtml {
                body {
                    div {
                        +"Hello, ${principal.name}"
                    }
                    div {
                        a(href = CommonRoutes.LOGOUT) {
                            +"Logout"
                        }
                    }
                }
            }
        }
    }
}