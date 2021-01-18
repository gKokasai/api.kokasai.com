package com.kokasai.flowerkt.css

import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.response.respondText
import kotlinx.css.CSSBuilder
import kotlinx.css.RuleSet

suspend inline fun ApplicationCall.respondCss(builder: RuleSet) {
    respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
