package com.kokasai.api.http.user.form

import com.kokasai.api.http._dsl.onlyUser
import com.kokasai.api.http.group.form.getGroupFormListResponse
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable
import com.kokasai.api.http.group.form.GetListResponse as GetGroupFormListResponse

@Serializable
data class GetListResponse(val group: Map<String, GetGroupFormListResponse>)

val list: RouteAction = {
    get {
        onlyUser { user ->
            call.respond(GetListResponse(user.file.group.associateWith { getGroupFormListResponse(it) }))
        }
    }
}
