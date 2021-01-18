package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.authenticate
import com.kokasai.flowerkt.route.get
import com.kokasai.the23rd.constants.AuthName
import com.kokasai.the23rd.extension.html.a
import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import com.kokasai.the23rd.user.UserPrincipal
import io.ktor.application.call
import io.ktor.auth.principal
import io.ktor.html.respondHtmlTemplate
import kotlinx.html.div

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
