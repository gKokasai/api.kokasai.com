package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.Companion.headerCss
import io.ktor.application.*
import io.ktor.routing.*

fun Routing.cssRoutes() {
    get(HtmlRoute.Css.Header) {
        call.respondCss(headerCss)
    }
}