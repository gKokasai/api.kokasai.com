package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.flowerkt.html.*
import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.sessions.*

val logout = route {
    get {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(HtmlRoute.Login)
    }
}