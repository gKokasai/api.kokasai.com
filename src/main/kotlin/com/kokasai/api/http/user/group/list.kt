package com.kokasai.api.http.user.group

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
data class ListResponse(val group: List<String>)

val list: RouteAction = {
    get {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val user = User.get(principal.name)
            val group = user.file.group
            call.respond(ListResponse(group))
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
