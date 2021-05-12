package com.kokasai.api.http.user.document

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.user.User
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val document: List<String>)

val list: RouteAction = {
    get {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val user = User.get(principal.name)
            val document = user.file.getDocument()
            call.respond(GetListResponse(document))
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
