package com.gitlab.nitgc.kokasai.the23rd

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.launch() {
    routing {
        get("/") {
            call.respondText("HelloWorld")
        }
    }
}