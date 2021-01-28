package com.kokasai.the23rd.configure

import com.kokasai.flowerkt.session.SessionStorageExposed
import com.kokasai.the23rd.Kokasai23rd
import com.kokasai.the23rd.constants.Auth
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Sessions.Configuration.configureAuthCookie() {
    cookie<Auth.UserLogin.Data>(Auth.UserLogin.cookie, storage = SessionStorageExposed(Kokasai23rd.sessionTable)) {
        cookie.path = "/"
    }
}
