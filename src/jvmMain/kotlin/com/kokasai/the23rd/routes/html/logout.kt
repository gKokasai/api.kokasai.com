package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.html.respondRedirect
import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.the23rd.constants.SessionConstants
import io.ktor.application.call
import io.ktor.sessions.sessions

val logout = buildGetRoute {
    call.sessions.clear(SessionConstants.AUTH)
    call.respondRedirect(HtmlRoute.Login)
}
