package com.kokasai.the23rd.routes

import com.kokasai.the23rd.Kokasai23rd
import io.ktor.application.call
import io.ktor.http.content.static
import io.ktor.response.respondFile
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.fileRoutes() {
    static {
        get("file/{path...}") {
            val path = call.parameters.getAll("path") ?: return@get
            val file = Kokasai23rd.fileProvider.get(path.joinToString("/")) ?: return@get
            call.respondFile(file)
        }
    }
}
