package com.kokasai.api.routes.http

import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get

val index: RouteAction = {
    get {
        call.respond(HttpStatusCode.OK)
    }
}
