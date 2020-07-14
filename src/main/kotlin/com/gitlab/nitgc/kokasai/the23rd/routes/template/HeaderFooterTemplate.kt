package com.gitlab.nitgc.kokasai.the23rd.routes.template

import com.gitlab.nitgc.kokasai.the23rd.extension.styleCss
import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.margin
import kotlinx.html.BODY
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.footer
import kotlinx.html.header
import kotlinx.html.span

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<BODY>()

    override fun HTML.apply() {
        styleCss {
            backgroundColor = Color.red
        }
        body {
            styleCss {
                margin = "0"
            }
            header {
                styleCss {
                    backgroundColor = Color.aqua
                }
                span {
                    styleCss {
                        backgroundColor = Color.yellow
                    }
                    +"Header"
                }
            }
            insert(body)
            footer {
                styleCss {
                    backgroundColor = Color.green
                }
                span {
                    styleCss {
                        backgroundColor = Color.yellow
                    }
                    +"Footer"
                }
            }
        }
    }
}