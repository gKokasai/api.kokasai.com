package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.routing.*
import io.ktor.sessions.*
import kotlinx.html.*

val login = route {
    get {
        val principal = call.sessions.get<UserPrincipal>()
        if (principal != null) {
            call.respondRedirect(HtmlRoute.Account)
        } else {
            call.respondHtmlTemplate(WithHeaderTemplate("ログイン")) {
                body {
                    form(method = FormMethod.post) {
                        val queryParams = call.request.queryParameters
                        val errorMessage = when {
                            "invalid" in queryParams -> "ユーザー名かパスワードが間違っています"
                            else -> null
                        }
                        if (errorMessage != null) {
                            div {
                                style = "color:red;"
                                +errorMessage
                            }
                        }
                        textInput(name = AuthFormFields.USERNAME) {
                            placeholder = "user (${AuthTestLogin.USERNAME})"
                        }
                        passwordInput(name = AuthFormFields.PASSWORD) {
                            placeholder = "password (${AuthTestLogin.PASSWORD})"
                        }
                        submitInput {
                            value = "Login"
                        }
                    }
                }
            }
        }
    }
    authenticate(AuthName.FORM) {
        post {
            val principal = call.principal<UserPrincipal>()
            call.sessions.set(SessionConstants.AUTH, principal)
            call.respondRedirect(HtmlRoute.Account)
        }
    }
}