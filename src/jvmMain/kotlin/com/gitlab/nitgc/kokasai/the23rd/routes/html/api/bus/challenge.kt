package com.gitlab.nitgc.kokasai.the23rd.routes.html.api.bus

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

val challenge = get {
    BusTokenManager.challenge(call) {
        call.respond(HttpStatusCode.OK, it)
    }
}
