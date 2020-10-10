package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.http.content.*
import io.ktor.routing.*

fun Routing.staticRoute() {
    static {
        resources("static")
    }
}