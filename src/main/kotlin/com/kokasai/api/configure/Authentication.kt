package com.kokasai.api.configure

import com.kokasai.api.auth.OnetimePasswordManager
import com.kokasai.api.auth.UserLogin
import io.ktor.application.call
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.auth.session
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun Authentication.Configuration.configureFormAuth() {
    basic(UserLogin.authName) {
        realm = UserLogin.realm
        validate {
            if (OnetimePasswordManager.auth(it.name, it.password)) {
                UserLogin.Data(it.name)
            } else {
                null
            }
        }
    }
}

fun Authentication.Configuration.configureSessionAuth() {
    session<UserLogin.Data> {
        challenge {
            call.respond(HttpStatusCode.Unauthorized)
        }
        validate { session ->
            session
        }
    }
}
