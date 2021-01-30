package com.kokasai.the23rd.routes.http.bus

import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get

val challenge: RouteAction = {
    get {
        BusTokenManager.challenge(call) {
            call.respond(HttpStatusCode.OK, it)
        }
    }
}
