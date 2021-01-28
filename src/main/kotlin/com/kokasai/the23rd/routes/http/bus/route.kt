package com.kokasai.the23rd.routes.http.bus

import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.the23rd.bus.BusRoute
import io.ktor.application.call
import io.ktor.response.respond

val route = buildGetRoute {
    BusTokenManager.challenge(call) {
        call.respond(BusRoute.route)
    }
}
