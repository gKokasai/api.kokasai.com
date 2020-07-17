package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.CSSBuilder
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.Float
import kotlinx.css.FontWeight
import kotlinx.css.ListStyleType
import kotlinx.css.Position
import kotlinx.css.TextAlign
import kotlinx.css.TextTransform
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.em
import kotlinx.css.float
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.fontWeight
import kotlinx.css.height
import kotlinx.css.listStyleType
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.css.position
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.Timing
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.css.px
import kotlinx.css.textAlign
import kotlinx.css.textDecoration
import kotlinx.css.textTransform
import kotlinx.css.width
import kotlinx.html.BODY
import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.li
import kotlinx.html.link
import kotlinx.html.span
import kotlinx.html.ul

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<BODY>()
    val THEME_COLOR = Color.green.withAlpha(0.6).blend(Color.white.withAlpha(0.4))
    val SELECTED_THEME_COLOR = Color.green.withAlpha(0.7).blend(Color.white.withAlpha(0.3))

    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/header.css", type = "text/css")
        }
        body {
            header {
                h1 {
                    +"工華祭"
                }
                ul {
                    li {
                        a(href = "#") {
                            +"Page1"
                        }
                    }
                    li {
                        a(href = "#") {
                            +"Page2"
                        }
                    }
                }
            }
            insert(body)
            footer {
                span {
                    //+"Footer"
                }
            }
        }
    }

    val headerCss: CSSBuilder.() -> Unit = {
        "body".invoke {
            margin = "0"
        }
        "header".invoke {
            backgroundColor = THEME_COLOR
            padding = "0.5em 0"
            height = 3.em
            boxShadow(Color.black.withAlpha(0.4), offsetY = 0.1.em, blurRadius = 0.2.em)
        }
        "header h1".invoke {
            color = Color.white
            fontSize = 2.em
            margin = "auto 1em"
            float = Float.left
        }
        "header ul".invoke {
            width = 100.pct
            margin = "0"
            padding = "0"
            listStyleType = ListStyleType.none
        }
        "header li".invoke {
            margin = "0"
            padding = "0"
            float = Float.right
            position = Position.relative
        }
        "header li a".invoke {
            padding = "10px 25px"
            display = Display.block
            fontFamily = "Tahoma"
            textTransform = TextTransform.uppercase
            fontSize = 18.px
            fontWeight = FontWeight.w400
            color = Color.white
            textAlign = TextAlign.center
            textDecoration = TextDecoration.none
            transition(property = "all", duration = .25.s, timing = Timing.ease)
        }
        "header li:hover a".invoke {
            backgroundColor = SELECTED_THEME_COLOR
        }
    }
}