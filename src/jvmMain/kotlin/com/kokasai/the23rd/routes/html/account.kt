package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.html.*
import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.constants.*
import com.kokasai.the23rd.routes.template.*
import com.kokasai.the23rd.user.*
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