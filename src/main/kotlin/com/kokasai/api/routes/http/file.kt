package com.kokasai.api.routes.http

import com.kokasai.api.KokasaiApi
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

val file: RouteAction = {
    get("{path...}") {
        val path = call.parameters.getAll("path")
        if (path != null && path.isNotEmpty()) {
            val file = api.fileProvider.get("public/" + path.joinToString("/"))
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
