package com.kokasai.api.http

import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.routing.get

val index: RouteAction = {
    get {
        call.respondRedirect("https://github.com/gKokasai/api.kokasai.com/blob/master/DOCUMENT.md")
    }
}
