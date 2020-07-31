package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.Routes
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate
import com.gitlab.nitgc.kokasai.the23rd.user.UserPrincipal
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlinx.html.*

fun Routing.accountRoute() {
    authenticate(AuthName.SESSION) {
        get(Routes.ACCOUNT) {
            val principal = call.principal<UserPrincipal>()!!
            call.respondHtmlTemplate(WithHeaderTemplate) {
                body {
                    div {
                        +"Hello, ${principal.name}"
                    }
                    div {
                        a(href = Routes.LOGOUT) {
                            +"Logout"
                        }
                    }
                }
            }
        }
    }
}