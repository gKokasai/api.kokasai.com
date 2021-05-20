package com.kokasai.api.http.group

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: List<String>)

val list: RouteAction = {
    get {
        onlyAdmin {
            call.respond(GetListResponse(Group.list()))
        }
    }
}
