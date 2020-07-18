package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import com.gitlab.nitgc.kokasai.the23rd.routes.template.HeaderFooterTemplate
import io.ktor.application.call
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.*

fun Route.profileRoute() {
    authenticate(AuthName.SESSION) {
        get(CommonRoutes.PROFILE) {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondHtmlTemplate(HeaderFooterTemplate) {
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