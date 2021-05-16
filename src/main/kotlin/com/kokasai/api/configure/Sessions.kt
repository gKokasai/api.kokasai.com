package com.kokasai.api.configure

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.auth.UserLogin
import com.kokasai.flowerkt.session.SessionStorageExposed
import io.ktor.sessions.Sessions
import io.ktor.sessions.header

fun Sessions.Configuration.configureAuthCookie() {
    header<UserLogin.Data>(UserLogin.sessionHeader, storage = SessionStorageExposed(api.sessionTable))
}
