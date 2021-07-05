package com.kokasai.api.http

import com.kokasai.api.document.Document
import com.kokasai.api.http._dsl.nowLogin
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.user.User.Companion.isAdmin
import com.kokasai.api.util.call.receiveFile
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import io.ktor.routing.post

val document: RouteAction = {
    get("{documentName}") {
        nowLogin { user ->
            parameter("documentName") { documentName ->
                if (user.isAdmin || user.file.getDocument().contains(documentName)) {
                    val file = Document.get(documentName)
                    if (file != null) {
                        call.respondFile(file)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
    post("{documentName}") {
        onlyAdmin {
            parameter("documentName") { documentName ->
                val file = call.receiveFile()
                if (file != null) {
                    Document.post(documentName, file)
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
