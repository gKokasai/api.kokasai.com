package com.kokasai.the23rd.configure

import com.kokasai.flowerkt.session.*
import com.kokasai.the23rd.*
import com.kokasai.the23rd.constants.*
import com.kokasai.the23rd.user.*
import io.ktor.sessions.*

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = SessionStorageExposed(Kokasai23rd.sessionTable)) {
        cookie.path = "/"
    }
}