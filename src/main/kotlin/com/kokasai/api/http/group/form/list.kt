package com.kokasai.api.http.group.form

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.group.Group
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
data class GetListResponse(val form: List<String>)

val list: RouteAction = {
    get("{groupName}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["groupName"]
            if (groupName != null) {
                val userName = principal.name
                val user = User.get(userName)
                val groups = user.file.group
                if (groups.contains(groupName)) {
                    val group = Group.get(groupName)
                    call.respond(GetListResponse(group.file.form))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
