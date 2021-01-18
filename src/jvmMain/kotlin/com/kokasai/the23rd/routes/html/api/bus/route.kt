package com.kokasai.the23rd.routes.html.api.bus

import com.kokasai.flowerkt.route.get
import com.kokasai.the23rd.constants.bus.BusRoute
import io.ktor.application.call
import io.ktor.response.respond

val route = get {
    BusTokenManager.challenge(call) {
        call.respond(BusRoute.route)
    }
}
