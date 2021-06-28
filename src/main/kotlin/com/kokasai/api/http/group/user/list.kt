package com.kokasai.api.http.group.user

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.inGroupFromParameter
import com.kokasai.api.http._dsl.onlyAdminOrOwner
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val owner: List<String>, val member: List<String>)

@Serializable
data class PostListRequest(val owner: List<String>, val member: List<String>)

val list: RouteAction = {
    get("{groupName}") {
        inGroupFromParameter("groupName") { user, groupName ->
            val group = Group.get(groupName)
            val members = group.file.member
            if (members.contains(user.name) || user.isAdmin) {
                call.respond(GetListResponse(group.file.owner, group.file.member))
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
    post("{groupName}") {
        parameter("groupName") { groupName ->
            onlyAdminOrOwner(groupName) { user, group ->
                val request = call.receive<PostListRequest>()
                val member = request.member
                val lastMember = group.file.member
                member.forEach {
                    if (lastMember.contains(it).not()) {
                        User.get(it).apply {
                            file.group.add(groupName)
                        }.save()
                    }
                }
                lastMember.forEach {
                    if (member.contains(it).not()) {
                        User.get(it).apply {
                            file.group.remove(groupName)
                        }.save()
                    }
                }
                group.file.member = member
                if (user.isAdmin) {
                    group.file.owner = request.owner
                }
                group.save()
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
