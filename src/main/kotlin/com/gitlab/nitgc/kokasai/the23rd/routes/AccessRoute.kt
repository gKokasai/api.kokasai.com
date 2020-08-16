package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.accessRoute() {
    route("/access") {
        get("/bus") {
            call.respondHtmlTemplate(WithHeaderTemplate(listOf("/bus_updater.js"))) {

            }
        }
    }
}