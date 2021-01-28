package com.kokasai.the23rd.configure

import com.kokasai.the23rd.constants.Auth
import io.ktor.application.call
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.auth.session
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun Authentication.Configuration.configureFormAuth() {
    basic(Auth.UserLogin.authName) {
        realm = Auth.UserLogin.realm
        validate {
            if (it.name == Auth.UserLogin.Test.Username && it.password == Auth.UserLogin.Test.Password) {
                Auth.UserLogin.Data(it.name)
            } else {
                null
            }
        }
    }
}

fun Authentication.Configuration.configureSessionAuth() {
    session<Auth.UserLogin.Data>(Auth.UserLogin.sessionName) {
        challenge {
            call.respond(HttpStatusCode.Unauthorized)
        }
        validate { session ->
            session
        }
    }
}
