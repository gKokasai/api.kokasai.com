package com.kokasai.api.http.group.document

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.inGroupFromParameter
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.util.Directory
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
            val document = if (groupName != Group.Name.admin) {
                Group.get(groupName).file.document
            } else {
                api.fileProvider.list(Directory.document).orEmpty()
            }
            call.respond(GetListResponse(document))
        }
    }
    post("{groupName}") {
        parameter("groupName") { groupName ->
            onlyAdmin {
                if (groupName != Group.Name.admin) {
                    val request = call.receive<PostListRequest>()
                    Group.get(groupName).apply {
                        file.document = request.document
                    }.save()
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
    }
}
