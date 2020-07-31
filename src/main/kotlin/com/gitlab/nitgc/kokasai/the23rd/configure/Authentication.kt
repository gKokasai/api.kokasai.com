package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthFormFields
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthTestLogin
import com.gitlab.nitgc.kokasai.the23rd.constants.Routes
import com.gitlab.nitgc.kokasai.the23rd.user.UserPrincipal
import io.ktor.application.call
import io.ktor.auth.Authentication
import io.ktor.auth.AuthenticationFailedCause
import io.ktor.auth.authentication
import io.ktor.auth.form
import io.ktor.auth.session
import io.ktor.response.respondRedirect

fun Authentication.Configuration.configureFormAuth() {
    form(AuthName.FORM) {
        userParamName = AuthFormFields.USERNAME
        passwordParamName = AuthFormFields.PASSWORD
        challenge {
            val failures = call.authentication.allFailures
            call.respondRedirect(
                when (failures.singleOrNull()) {
                    AuthenticationFailedCause.InvalidCredentials -> {
                        "${Routes.LOGIN}?invalid"
                    }
                    else -> {
                        Routes.LOGIN
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
            call.respondRedirect(Routes.LOGIN)
        }
        validate { session ->
            session
        }
    }
}