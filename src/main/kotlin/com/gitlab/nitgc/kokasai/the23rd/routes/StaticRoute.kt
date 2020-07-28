package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.Routing

fun Routing.staticRoute() {
    static {
        resources("static")
    }
}