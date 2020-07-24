package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.headerJs
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.jsRoutes() {
    get("/header.js") {
        call.respondText(headerJs)
    }
}