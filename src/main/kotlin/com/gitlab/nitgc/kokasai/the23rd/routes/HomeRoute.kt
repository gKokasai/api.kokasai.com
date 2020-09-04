package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*

fun Routing.homeRoute() {
    get(HtmlRoute.Home) {
        call.respondHtmlTemplate(WithHeaderTemplate(null)) {
            body {

            }
        }
    }
}