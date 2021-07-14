package com.kokasai.api.http.group.document

import com.kokasai.api.document.Document
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val document: List<String>)

@Serializable
data class PostListRequest(val document: List<String>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdminOrGroupUser(groupName) {
            val document = if (groupName != Group.Name.admin) {
                Group.get(groupName).document
            } else {
                Document.list()
            }
            call.respond(GetListResponse(document))
        }
    }
}

val listPost: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdmin {
            if (groupName != Group.Name.admin) {
                val request = call.receive<PostListRequest>()
                Group.get(groupName).edit {
                    document = request.document
                }
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}
