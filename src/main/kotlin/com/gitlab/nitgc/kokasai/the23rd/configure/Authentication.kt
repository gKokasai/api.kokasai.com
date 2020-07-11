package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.AuthFormFields
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthName
import com.gitlab.nitgc.kokasai.the23rd.constants.AuthTestLogin
import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import io.ktor.application.call
import io.ktor.auth.*
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
                            "${CommonRoutes.LOGIN}?invalid"
                        }
                        AuthenticationFailedCause.NoCredentials -> {
                            "${CommonRoutes.LOGIN}?no"
                        }
                        else -> {
                            CommonRoutes.LOGIN
                        }
                    }
            )
        }
        validate {
            if (it.name == AuthTestLogin.USERNAME && it.password == AuthTestLogin.PASSWORD) {
                UserIdPrincipal(it.name)
            } else {
                null
            }
        }
    }
}

fun Authentication.Configuration.configureSessionAuth() {
    session<UserIdPrincipal>(AuthName.SESSION) {
        challenge {
            call.respondRedirect("${CommonRoutes.LOGIN}?no")
        }
        validate { session ->
            session
        }
    }
}