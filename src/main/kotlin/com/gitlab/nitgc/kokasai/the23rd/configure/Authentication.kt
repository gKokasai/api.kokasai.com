package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*

fun Authentication.Configuration.configureFormAuth() {
    form(AuthName.FORM) {
        userParamName = AuthFormFields.USERNAME
        passwordParamName = AuthFormFields.PASSWORD
        challenge {
            val failures = call.authentication.allFailures
            call.respondRedirect(
                when (failures.singleOrNull()) {
                    AuthenticationFailedCause.InvalidCredentials -> {
                        "${HtmlRoute.Login.fullpath}?invalid"
                    }
                    else -> {
                        HtmlRoute.Login.fullpath
                    }
                }
            )
        }
        validate {
            if (it.name == AuthTestLogin.USERNAME && it.password == AuthTestLogin.PASSWORD) {
                UserPrincipal(it.name)
            } else {
                null
            }
        }
    }
}

fun Authentication.Configuration.configureSessionAuth() {
    session<UserPrincipal>(AuthName.SESSION) {
        challenge {
            call.respondRedirect(HtmlRoute.Login.fullpath)
        }
        validate { session ->
            session
        }
    }
}