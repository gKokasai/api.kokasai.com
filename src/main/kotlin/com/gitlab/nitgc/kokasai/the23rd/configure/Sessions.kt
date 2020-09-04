package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.sessions.*
import java.io.*

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = directorySessionStorage(File(".sessions"), true)) {
        cookie.path = "/"
    }
}