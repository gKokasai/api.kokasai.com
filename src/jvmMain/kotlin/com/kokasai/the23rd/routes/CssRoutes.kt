package com.kokasai.the23rd.routes

import com.kokasai.flowerkt.css.respondCss
import com.kokasai.the23rd.routes.html.HtmlRoute
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.cssRoutes() {
    HtmlRoute.Css::class.sealedSubclasses.forEach { kClass ->
        kClass.objectInstance?.let { objectInstance ->
            objectInstance.response?.let { response ->
                get(objectInstance.path) {
                    call.respondCss(response)
                }
            }
        }
    }
}
