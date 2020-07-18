package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.*
import kotlinx.css.Float
import kotlinx.css.properties.*
import kotlinx.html.*

object HeaderFooterTemplate: Template<HTML> {
    val body = Placeholder<BODY>()
    val THEME_COLOR = Color.green.withAlpha(0.6).blend(Color.white)
    val SELECTED_THEME_COLOR = Color.green.withAlpha(0.7).blend(Color.white)

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
        "body" {
            margin = "0"
        }
        "header" {
            backgroundColor = THEME_COLOR
            padding = "0.5em 0"
            height = 3.em
            boxShadow(Color.black.withAlpha(0.4), offsetY = 0.1.em, blurRadius = 0.2.em)
        }
        "header h1" {
            color = Color.white
            fontSize = 2.em
            margin = "auto 1em"
            float = Float.left
        }
        "header ul" {
            width = 100.pct
            margin = "0"
            padding = "0"
            listStyleType = ListStyleType.none
        }
        "header li" {
            margin = "0"
            padding = "0"
            float = Float.right
            position = Position.relative
        }
        "header li a" {
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
        "header li:hover a" {
            backgroundColor = SELECTED_THEME_COLOR
        }
    }
}