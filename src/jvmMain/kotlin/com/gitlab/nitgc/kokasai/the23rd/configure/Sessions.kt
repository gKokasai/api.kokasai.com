package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.sessions.*
import java.time.*

val sessionTable = SessionTable("session", Duration.ofDays(30))

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = SessionStorageExposed(sessionTable)) {
        cookie.path = "/"
    }
}