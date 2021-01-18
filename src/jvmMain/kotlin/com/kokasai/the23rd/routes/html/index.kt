package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate

val index = buildGetRoute {
    call.respondHtmlTemplate(WithHeaderTemplate(null)) {
        body {
        }
    }
}
