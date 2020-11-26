package com.kokasai.the23rd.routes

import com.kokasai.the23rd.routes.html.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Route.staticRoute() {
    static {
        resource(HtmlRoute.Js.MainBundle.path)
    }
}