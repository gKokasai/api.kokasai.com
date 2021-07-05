package com.kokasai.api.http.user.document

import com.kokasai.api.http._dsl.nowLogin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val document: List<String>)

val list: RouteAction = {
    get {
        nowLogin { user ->
            val document = user.file.getDocument()
            call.respond(GetListResponse(document))
        }
    }
}
