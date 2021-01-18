package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.html.respondRedirect
import com.kokasai.flowerkt.route.get
import com.kokasai.flowerkt.route.route
import com.kokasai.the23rd.constants.SessionConstants
import io.ktor.application.call
import io.ktor.sessions.sessions

val logout = route {
    get {
        call.sessions.clear(SessionConstants.AUTH)
        call.respondRedirect(HtmlRoute.Login)
    }
}
