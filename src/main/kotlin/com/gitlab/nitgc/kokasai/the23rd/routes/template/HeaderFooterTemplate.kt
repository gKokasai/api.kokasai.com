package com.gitlab.nitgc.kokasai.the23rd.routes.template

import com.gitlab.nitgc.kokasai.the23rd.extension.styleCss
import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.em
import kotlinx.css.fontSize
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.paddingLeft
import kotlinx.css.pct
import kotlinx.html.BODY
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.footer
import kotlinx.html.header
import kotlinx.html.span

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<BODY>()

    override fun HTML.apply() {
        body {
            styleCss {
                margin = "0"
            }
            header {
                styleCss {
                    backgroundColor = Color.green.withAlpha(0.5)
                    padding = "1em 0"
                }
                span {
                    styleCss {
                        fontSize = 2.em
                        paddingLeft = 3.pct
                    }
                    +"工華祭"
                }
            }
            insert(body)
            footer {
                span {
                    +"Footer"
                }
            }
        }
    }
}