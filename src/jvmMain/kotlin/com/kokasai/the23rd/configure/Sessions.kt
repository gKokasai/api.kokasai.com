package com.kokasai.the23rd.configure

import com.kokasai.flowerkt.session.SessionStorageExposed
import com.kokasai.the23rd.Kokasai23rd
import com.kokasai.the23rd.constants.CookiesConstants
import com.kokasai.the23rd.user.UserPrincipal
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = SessionStorageExposed(Kokasai23rd.sessionTable)) {
        cookie.path = "/"
    }
}
