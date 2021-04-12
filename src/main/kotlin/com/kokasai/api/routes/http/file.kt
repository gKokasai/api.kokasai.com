package com.kokasai.api.routes.http

import com.kokasai.flowerkt.route.RouteAction
import com.kokasai.api.KokasaiAPI
import io.ktor.application.call
import io.ktor.response.respondFile
import io.ktor.routing.get

val file: RouteAction = {
    get("{path...}") {
        val path = call.parameters.getAll("path") ?: return@get
        val file = KokasaiAPI.fileProvider.get(path.joinToString("/")) ?: return@get
        call.respondFile(file)
    }
}
