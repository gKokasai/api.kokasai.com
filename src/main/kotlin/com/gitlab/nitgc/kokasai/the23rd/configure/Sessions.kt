package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.the23rd.constants.CookiesConstants
import com.gitlab.nitgc.kokasai.the23rd.user.UserPrincipal
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.directorySessionStorage
import java.io.File

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = directorySessionStorage(File(".sessions"), true)) {
        cookie.path = "/"
    }
}