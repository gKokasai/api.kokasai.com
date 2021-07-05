package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.http._dsl.nowLogin
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable

@Serializable
data class GetResponse(val count: Long)

val session: RouteAction = {
    get {
        nowLogin { user ->
            call.respond(GetResponse(UserLogin.getSessionCount(user.name)))
        }
    }
}
