package com.kokasai.api.http

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.util.Directory
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get

val file: RouteAction = {
    get("{path...}") {
        val path = call.parameters.getAll("path")
        if (path != null && path.isNotEmpty()) {
            val file = api.fileProvider.get("${Directory.public}/" + path.joinToString("/"))
            if (file != null) {
                call.respondFile(file)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}
