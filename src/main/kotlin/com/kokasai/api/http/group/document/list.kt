package com.kokasai.api.http.group.document

import com.kokasai.api.document.Document
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.http._dsl.requestBody
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
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
                requestBody<PostListRequest> { (document) ->
                    Group.get(groupName).edit {
                        this.document = document
                    }
                    call.respond(HttpStatusCode.OK)
                }
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}
