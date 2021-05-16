package com.kokasai.api.configure

import com.kokasai.api.auth.UserLogin
import io.ktor.features.CORS
import io.ktor.http.HttpHeaders

fun CORS.Configuration.configureCORS() {
    anyHost()
    header(HttpHeaders.Authorization)
    header(HttpHeaders.ContentType)
    header(UserLogin.sessionHeader)
    header("withCredentials")
    exposeHeader(UserLogin.sessionHeader)
    host("kokasai.com", schemes = listOf("http", "https"), subDomains = listOf("panel"))
    allowCredentials = true
}
