package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.CookiesConstants
import io.ktor.auth.UserIdPrincipal
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserIdPrincipal>(CookiesConstants.AUTH, storage = SessionStorageMemory()) {
        cookie.path = "/"
    }
}