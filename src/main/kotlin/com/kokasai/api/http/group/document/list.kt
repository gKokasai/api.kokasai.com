package com.kokasai.api.http.group.document

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.group.Group
import com.kokasai.api.user.User
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions

data class ListRequest(val document: List<String>)
data class ListResponse(val document: List<String>)

val list: RouteAction = {
    get("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["name"]
            if (groupName != null) {
                val userName = principal.name
                val user = User.get(userName)
                val groups = user.file.group
                if (groups.contains(groupName)) {
                    val group = Group.get(groupName)
                    call.respond(ListResponse(group.file.document))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "name is not set.")
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
    post("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["name"]
            if (groupName != null) {
                val userName = principal.name
                val user = User.get(userName)
                val groups = user.file.group
                if (groups.contains(Group.Name.admin)) {
                    val group = Group.get(groupName)
                    val request = call.receive<ListRequest>()
                    group.file.document = request.document
                    group.save()
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "name is not set.")
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
