package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.HtmlRoute
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Routing

fun Routing.accessRoute() {
    route(HtmlRoute.Access) {
        get(HtmlRoute.Access.Bus) {
            call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス", javaScripts = listOf(HtmlRoute.Js.BusUpdater))) {

            }
        }
    }
}