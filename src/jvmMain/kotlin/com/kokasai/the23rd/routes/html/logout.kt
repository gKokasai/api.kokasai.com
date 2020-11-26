package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.html.*
import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.constants.*
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.sessions.*

val logout = route {
    get {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(HtmlRoute.Login)
    }
}