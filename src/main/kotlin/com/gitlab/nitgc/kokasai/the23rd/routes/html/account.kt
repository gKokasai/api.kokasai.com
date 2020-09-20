package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

val account = authenticate(AuthName.SESSION) {
    get {
        val principal = call.principal<UserPrincipal>()!!
        call.respondHtmlTemplate(WithHeaderTemplate("アカウント")) {
            body {
                div {
                    +"Hello, ${principal.name}"
                }
                div {
                    a(href = HtmlRoute.Logout) {
                        +"Logout"
                    }
                }
            }
        }
    }
}