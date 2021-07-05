package com.kokasai.api.http.group.user

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdminOrGroupOwner
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
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
        parameter("groupName") { groupName ->
            onlyAdminOrGroupUser(groupName) { user ->
                val group = Group.get(groupName)
                val members = group.file.member
                if (members.contains(user.name) || user.isAdmin) {
                    call.respond(GetListResponse(group.file.owner, group.file.member))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
    }
    post("{groupName}") {
        parameter("groupName") { groupName ->
            onlyAdminOrGroupOwner(groupName) { user, group ->
                val request = call.receive<PostListRequest>()
                val lastAllUser = group.file.allUser
                if (user.isAdmin) {
                    group.file.owner = request.owner
                }
                val owners = group.file.owner
                group.file.member = request.member.filterNot(owners::contains)
                val allUser = group.file.allUser
                allUser.forEach {
                    if (lastAllUser.contains(it).not()) {
                        User.get(it).apply {
                            file.group.add(groupName)
                        }.save()
                    }
                }
                lastAllUser.forEach {
                    if (allUser.contains(it).not()) {
                        User.get(it).apply {
                            file.group.remove(groupName)
                        }.save()
                    }
                }
                group.save()
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
