package com.kokasai.the23rd.routes.html.access

import com.kokasai.flowerkt.route.buildGetRoute
import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate

val bus = buildGetRoute {
    call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス")) {
    }
}
