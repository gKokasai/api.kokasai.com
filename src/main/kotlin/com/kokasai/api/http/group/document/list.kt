package com.kokasai.api.http.group.document

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.inGroupFromParameter
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.parameter
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val document: List<String>)

@Serializable
data class PostListRequest(val document: List<String>)

val list: RouteAction = {
    get("{groupName}") {
        inGroupFromParameter("groupName") { _, groupName ->
            val group = Group.get(groupName)
            call.respond(GetListResponse(group.file.document))
        }
    }
    post("{groupName}") {
        parameter("groupName") { groupName ->
            onlyAdmin {
                val group = Group.get(groupName)
                val request = call.receive<PostListRequest>()
                group.file.document = request.document
                group.save()
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
