package com.kokasai.api.http

import com.kokasai.api.KokasaiApi
import com.kokasai.api.auth.UserLogin
import com.kokasai.api.user.User
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

val document: RouteAction = {
    get("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val documentName = call.parameters["name"]
            val user = User.get(principal.name)
            val accessibleDocument = user.file.getDocument()
            if (accessibleDocument.contains(documentName)) {
                if (documentName != null) {
                    val file = api.fileProvider.get("document/$documentName")
                    if (file != null) {
                        call.respondFile(file)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
