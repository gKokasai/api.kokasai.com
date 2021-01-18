package com.kokasai.the23rd.routes.html.api.bus

import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.the23rd.constants.bus.BusRoute
import io.ktor.application.call
import io.ktor.response.respond

val route = buildGetRoute {
    BusTokenManager.challenge(call) {
        call.respond(BusRoute.route)
    }
}
