package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlinx.html.*

fun Routing.testRoute() {
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