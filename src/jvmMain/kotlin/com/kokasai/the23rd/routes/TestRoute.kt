package com.kokasai.the23rd.routes

import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.h4
import kotlinx.html.h5
import kotlinx.html.h6
import kotlinx.html.p

fun Route.testRoute() {
    get("/test") {
        call.respondHtmlTemplate(WithHeaderTemplate("テスト")) {
            body {
                h2 {
                    +"h2 h2 h2 h2"
                }
                h3 {
                    +"h3 h3 h3 h3"
                }
                h4 {
                    +"h4 h4 h4 h4"
                }
                h5 {
                    +"h5 h5 h5 h5"
                }
                h6 {
                    +"h6 h6 h6 h6"
                }
                p {
                    +"p p p p"
                }
            }
        }
    }
}
