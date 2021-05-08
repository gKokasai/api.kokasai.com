package com.kokasai.api.http

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.auth.UserLogin
import com.kokasai.api.user.User
import com.kokasai.api.util.Directory
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse(val document: List<String>)

val document: RouteAction = {
    get {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            if (User.isAdmin(principal.name)) {
                call.respond(ListResponse(api.fileProvider.list(Directory.document).orEmpty()))
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
    get("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val documentName = call.parameters["name"]!!
            val user = User.get(principal.name)
            val accessibleDocument = user.file.getDocument()
            if (accessibleDocument.contains(documentName)) {
                val file = api.fileProvider.get("${Directory.document}/$documentName")
                if (file != null) {
                    call.respondFile(file)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
