package com.kokasai.api.configure

import com.kokasai.api.KokasaiApi
import com.kokasai.api.auth.UserLogin
import com.kokasai.flowerkt.session.SessionStorageExposed
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

fun Sessions.Configuration.configureAuthCookie() {
    cookie<UserLogin.Data>(UserLogin.cookie, storage = SessionStorageExposed(api.sessionTable)) {
        cookie.path = "/"
    }
}
