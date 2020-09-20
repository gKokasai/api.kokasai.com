package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.*
import io.ktor.routing.*

fun Routing.cssRoutes() {
    HtmlRoute.Css::class.sealedSubclasses.forEach { kClass ->
        kClass.objectInstance?.let { objectInstance ->
            objectInstance.response?.let { response ->
                route(objectInstance) {
                    get {
                        call.respondCss(response)
                    }
                }
            }
        }
    }
}