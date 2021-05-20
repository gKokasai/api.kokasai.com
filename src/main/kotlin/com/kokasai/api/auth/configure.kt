package com.kokasai.api.auth

import com.kokasai.api.KokasaiApi
import com.kokasai.flowerkt.session.SessionStorageExposed
import io.ktor.application.call
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.auth.session
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.sessions.Sessions
import io.ktor.sessions.header

fun Authentication.Configuration.configureBasicAuth() {
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

fun Sessions.Configuration.configureSessionHeader() {
    header<UserLogin.Data>(UserLogin.sessionHeader, storage = SessionStorageExposed(KokasaiApi.api.sessionTable))
}
