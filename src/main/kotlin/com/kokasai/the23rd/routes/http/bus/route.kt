package com.kokasai.the23rd.routes.http.bus

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.the23rd.bus.BusRoute
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get

val route: RouteAction = {
    get {
        BusTokenManager.challenge(call) {
            call.respond(BusRoute.route)
        }
    }
}
