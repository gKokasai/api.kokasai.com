package com.kokasai.api.http.group.form

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.inGroupFromParameter
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val form: List<String>)

val list: RouteAction = {
    get("{groupName}") {
        inGroupFromParameter("groupName") { _, groupName ->
            val group = Group.get(groupName)
            call.respond(GetListResponse(group.file.form))
        }
    }
}
