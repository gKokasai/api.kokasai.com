package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.header

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<FlowContent>()

    override fun HTML.apply() {
        body {
            header {
                insert(HeaderTemplate) {}
            }
            insert(body)
            footer {
                insert(FooterTemplate) {}
            }
        }
    }
}

object HeaderTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            +"Header"
        }
    }
}

object FooterTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            +"Footer"
        }
    }
}