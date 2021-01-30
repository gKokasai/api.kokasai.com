package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.the23rd.Kokasai23rd
import io.ktor.application.call
import io.ktor.response.respondFile
import io.ktor.routing.get

val file: RouteAction = {
    get("{path...}") {
        val path = call.parameters.getAll("path") ?: return@get
        val file = Kokasai23rd.fileProvider.get(path.joinToString("/")) ?: return@get
        call.respondFile(file)
    }
}
