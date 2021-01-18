package com.kokasai.the23rd.routes

import com.kokasai.the23rd.routes.html.HtmlRoute
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.routing.Route

fun Route.staticRoute() {
    static {
        resource(HtmlRoute.Js.MainBundle.path)
    }
}
