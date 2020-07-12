package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.BODY
import kotlinx.html.FOOTER
import kotlinx.html.HEADER
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.footer
import kotlinx.html.header

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<BODY>()

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

object HeaderTemplate: Template<HEADER> {
    override fun HEADER.apply() {
        +"Header"
    }
}

object FooterTemplate: Template<FOOTER> {
    override fun FOOTER.apply() {
        +"Footer"
    }
}