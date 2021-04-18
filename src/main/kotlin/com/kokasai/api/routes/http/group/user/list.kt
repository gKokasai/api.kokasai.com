package com.kokasai.api.routes.http.group.user

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

data class ListRequest(val member: List<String>)
data class ListResponse(val member: List<String>)

val list: RouteAction = {
    get("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["name"]
            if (groupName != null) {
                val userName = principal.name
                val group = Group.get(groupName)
                val owners = group.file?.owner
                if ((owners != null && owners.contains(userName)) || User.isAdmin(userName)) {
                    call.respond(ListResponse(group.file?.member.orEmpty()))
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
                val group = Group.get(groupName)
                val owners = group.file?.owner
                if ((owners != null && owners.contains(userName)) || User.isAdmin(userName)) {
                    val request = call.receive<ListRequest>()
                    val lastMember = group.file?.member.orEmpty()
                    val addMember = request.member.filterNot { lastMember.contains(it) }
                    addMember.forEach {
                        val member = User.get(it)
                        member.file?.group?.add(groupName)
                        member.save()
                    }
                    val removeMember = lastMember.filterNot { request.member.contains(it) }
                    removeMember.forEach {
                        val member = User.get(it)
                        member.file?.group?.remove(groupName)
                        member.save()
                    }
                    group.file?.member = request.member
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
