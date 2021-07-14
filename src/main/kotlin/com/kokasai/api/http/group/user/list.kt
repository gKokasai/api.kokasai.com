package com.kokasai.api.http.group.user

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdminOrGroupOwner
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val owner: List<String>, val member: List<String>)

@Serializable
data class PostListRequest(val owner: List<String>, val member: List<String>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdminOrGroupUser(groupName) { user ->
            val group = Group.get(groupName)
            val members = group.member
            if (members.contains(user.name) || user.isAdmin) {
                call.respond(GetListResponse(group.owner, group.member))
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}

val listPost: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdminOrGroupOwner(groupName) { user, group ->
            val request = call.receive<PostListRequest>()
            val lastAllUser = group.allUser
            group.edit {
                if (user.isAdmin) {
                    owner = request.owner
                }
                member = request.member.filterNot(owner::contains)
            }
            val allUser = group.allUser
            allUser.forEach {
                if (lastAllUser.contains(it).not()) {
                    User.get(it).edit {
                        this.group.add(groupName)
                    }
                }
            }
            lastAllUser.forEach {
                if (allUser.contains(it).not()) {
                    User.get(it).edit {
                        this.group.remove(groupName)
                    }
                }
            }
            call.respond(HttpStatusCode.OK)
        }
    }
}
