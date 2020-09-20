package com.gitlab.nitgc.kokasai.the23rd.routes.html.api.bus

import com.gitlab.nitgc.kokasai.the23rd.constants.bus.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.*
import io.ktor.response.*

val route = get {
    BusTokenManager.challenge(call) {
        call.respond(BusRoute.route)
    }
}