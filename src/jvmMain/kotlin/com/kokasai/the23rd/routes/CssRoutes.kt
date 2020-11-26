package com.kokasai.the23rd.routes

import com.kokasai.flowerkt.css.*
import com.kokasai.the23rd.routes.html.*
import io.ktor.application.*
import io.ktor.routing.*

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