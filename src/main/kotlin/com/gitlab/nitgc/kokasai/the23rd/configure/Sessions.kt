package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.sessions.*

val sessionTable = SessionTable("session")

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = SessionStorageExposed(sessionTable)) {
        cookie.path = "/"
    }
}