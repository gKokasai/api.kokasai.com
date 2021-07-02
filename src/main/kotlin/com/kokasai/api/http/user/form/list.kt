package com.kokasai.api.http.user.form

import com.kokasai.api.http._dsl.onlyUser
import com.kokasai.api.http.group.form.SimpleGroupFormData
import com.kokasai.api.http.group.form.getGroupFormListResponse
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: Map<String, Map<String, SimpleGroupFormData>>)

val list: RouteAction = {
    get {
        onlyUser { user ->
            call.respond(GetListResponse(user.file.group.associateWith { getGroupFormListResponse(it).form }))
        }
    }
}
