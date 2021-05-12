package com.kokasai.api.http

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.onlyUser
import com.kokasai.api.util.Directory
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetDocumentListResponse(val document: List<String>)

val document: RouteAction = {
    get {
        onlyAdmin {
            call.respond(GetDocumentListResponse(api.fileProvider.list(Directory.document).orEmpty()))
        }
    }
    get("{name}") {
        onlyUser { user ->
            val documentName = call.parameters["name"]!!
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
        }
    }
}
