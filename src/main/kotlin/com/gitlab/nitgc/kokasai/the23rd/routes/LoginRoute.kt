package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthFormFields
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthTestLogin
import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import com.gitlab.nitgc.kokasai.the23rd.constants.SessionConstants
import com.gitlab.nitgc.kokasai.the23rd.routes.template.HeaderFooterTemplate
import io.ktor.application.call
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.principal
import io.ktor.html.respondHtmlTemplate
import io.ktor.response.respondRedirect
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.sessions
import kotlinx.html.FormMethod
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.passwordInput
import kotlinx.html.style
import kotlinx.html.submitInput
import kotlinx.html.textInput

fun Routing.loginRoute() {
    route(CommonRoutes.LOGIN) {
        get {
            call.respondHtmlTemplate(HeaderFooterTemplate) {
                body {
                    form(method = FormMethod.post) {
                        val queryParams = call.request.queryParameters
                        val errorMessage = when {
                            "invalid" in queryParams -> "ユーザー名かパスコードが間違っています"
                            "no" in queryParams -> "ログインしていません"
                            else -> null
                        }
                        if(errorMessage != null) {
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
        authenticate(AuthName.FORM) {
            post {
                val principal = call.principal<UserIdPrincipal>()
                call.sessions.set(SessionConstants.AUTH, principal)
                call.respondRedirect(CommonRoutes.PROFILE)
            }
        }
    }
}