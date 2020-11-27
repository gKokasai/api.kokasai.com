package com.kokasai.the23rd.routes

import com.kokasai.the23rd.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.fileRoutes() {
    static {
        get("file/{path...}") {
            val path = call.parameters.getAll("path") ?: return@get
            val file = Kokasai23rd.fileProvider.get(path.joinToString("/")) ?: return@get
            call.respondFile(file)
        }
    }
}