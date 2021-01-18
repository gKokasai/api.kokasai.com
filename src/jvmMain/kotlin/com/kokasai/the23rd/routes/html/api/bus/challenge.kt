package com.kokasai.the23rd.routes.html.api.bus

import com.kokasai.flowerkt.route.get
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

val challenge = get {
    BusTokenManager.challenge(call) {
        call.respond(HttpStatusCode.OK, it)
    }
}
