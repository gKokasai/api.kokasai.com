package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.html.respondRedirect
import com.kokasai.flowerkt.route.buildRoute
import com.kokasai.the23rd.constants.AuthFormFields
import com.kokasai.the23rd.constants.AuthName
import com.kokasai.the23rd.constants.AuthTestLogin
import com.kokasai.the23rd.constants.SessionConstants
import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import com.kokasai.the23rd.user.UserPrincipal
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.FormMethod
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.passwordInput
import kotlinx.html.style
import kotlinx.html.submitInput
import kotlinx.html.textInput

val login = buildRoute {
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
