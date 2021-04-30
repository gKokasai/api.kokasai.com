package com.kokasai.api.configure

import io.ktor.features.CORS
import io.ktor.http.HttpHeaders

fun CORS.Configuration.configureCORS() {
    anyHost()
    header(HttpHeaders.Authorization)
    header(HttpHeaders.ContentType)
    header("withCredentials")
    exposeHeader(HttpHeaders.SetCookie)
    host("kokasai.com", schemes = listOf("http", "https"), subDomains = listOf("panel"))
    allowCredentials = true
}
