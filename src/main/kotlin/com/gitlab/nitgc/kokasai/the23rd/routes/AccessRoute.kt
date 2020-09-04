package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*

fun Routing.accessRoute() {
    route(HtmlRoute.Access) {
        get(HtmlRoute.Access.Bus) {
            call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス", javaScripts = listOf(HtmlRoute.Js.BusUpdater))) {

            }
        }
    }
}