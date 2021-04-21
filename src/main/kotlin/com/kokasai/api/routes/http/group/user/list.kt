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

data class ListRequest(val owner: List<String>, val member: List<String>)
data class ListResponse(val owner: List<String>, val member: List<String>)

val list: RouteAction = {
    get("{name}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["name"]
            if (groupName != null) {
                val userName = principal.name
                val group = Group.get(groupName)
                val members = group.file.member
                if (members.contains(userName) || User.isAdmin(userName)) {
                    call.respond(ListResponse(group.file.owner, group.file.member))
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
                val owners = group.file.owner
                val isAdmin = User.isAdmin(userName)
                if (owners.contains(userName) || isAdmin) {
                    val request = call.receive<ListRequest>()
                    val lastMember = group.file.member
                    val addMember = request.member.filterNot { lastMember.contains(it) }
                    addMember.forEach {
                        val member = User.get(it)
                        member.file.group.add(groupName)
                        member.save()
                    }
                    val removeMember = lastMember.filterNot { request.member.contains(it) }
                    removeMember.forEach {
                        val member = User.get(it)
                        member.file.group.remove(groupName)
                        member.save()
                    }
                    group.file.member = request.member
                    if (isAdmin) {
                        group.file.owner = request.owner
                    }
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
