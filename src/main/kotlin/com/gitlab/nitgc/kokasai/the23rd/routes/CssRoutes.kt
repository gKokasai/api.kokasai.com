package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.HtmlRoute
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.Companion.headerCss
import io.ktor.application.call
import io.ktor.routing.Routing

fun Routing.cssRoutes() {
    get(HtmlRoute.Css.Header) {
        call.respondCss(headerCss)
    }
}