package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.CommonRoutes
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.homeRoute() {
    route(CommonRoutes.HOME) {
        get {
            call.respondHtmlTemplate(WithHeaderTemplate) {
                body {

                }
            }
        }
    }
}