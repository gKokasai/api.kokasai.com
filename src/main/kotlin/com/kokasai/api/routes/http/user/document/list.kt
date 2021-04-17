package com.kokasai.api.routes.http.user.document

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

data class ListResponse(val document: List<String>)

val list: RouteAction = {
    get {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val user = User.get(principal.name)
            val group = user.file?.group?.map { Group.get(it) }
            val document = group?.mapNotNull { it.file?.document }?.flatten()
            call.respond(ListResponse(document.orEmpty()))
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
