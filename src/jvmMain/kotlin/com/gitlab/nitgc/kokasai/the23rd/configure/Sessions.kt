package com.gitlab.nitgc.kokasai.the23rd.configure

import com.gitlab.nitgc.kokasai.flowerkt.session.*
import com.gitlab.nitgc.kokasai.the23rd.*
import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.user.*
import io.ktor.sessions.*

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserPrincipal>(CookiesConstants.AUTH, storage = SessionStorageExposed(Kokasai23rd.sessionTable)) {
        cookie.path = "/"
    }
}