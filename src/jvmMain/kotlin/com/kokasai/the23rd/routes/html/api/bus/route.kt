package com.kokasai.the23rd.routes.html.api.bus

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.constants.bus.*
import io.ktor.application.*
import io.ktor.response.*

val route = get {
    BusTokenManager.challenge(call) {
        call.respond(BusRoute.route)
    }
}