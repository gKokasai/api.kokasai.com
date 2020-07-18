package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

object WithHeaderTemplate: Template<HTML> {
    val body = Placeholder<BODY>()
    val THEME_COLOR = Color.green.withAlpha(0.6).blend(Color.white)

    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/header.css", type = "text/css")
        }
        body {
            header {
                h1 {
                    a(href = "/") {
                        +"工華祭"
                    }
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
        }
    }

    val headerCss: CSSBuilder.() -> Unit = {
        "body" {
            margin(0.vh)
        }
        "li" {
            listStyleType = ListStyleType.none
        }
        "header" {
            display = Display.flex
            backgroundColor = THEME_COLOR
            height = 6.4.vh
            boxShadow(Color.black.withAlpha(0.4), offsetY = 0.16.vh, blurRadius = 0.32.vh)
        }
        "header h1" {
            fontSize = 3.2.vh
            width = 9.6.vh
            margin(LinearDimension.auto, 1.6.vh)
            paddingTop = 0.32.vh
        }
        "header h1 a" {
            color = Color.white
            textAlign = TextAlign.left
            textDecoration = TextDecoration.none
        }
        "header ul" {
            margin(LinearDimension.auto)
            marginRight = 0.vh
            padding(0.vh)
            paddingTop = 0.224.vh
        }
        "header li" {
            fontSize = 2.24.vh
            padding(0.vh, 2.24.vh)
            display = Display.inline
        }
        "header li+ li" {
            borderLeft(0.16.vh, BorderStyle.solid, Color.white)
        }
        "header li a" {
            fontWeight = FontWeight.w400
            color = Color.white
            textAlign = TextAlign.center
            textDecoration = TextDecoration.none
            position = Position.relative
            display = Display.inlineBlock
        }
        "header li a::after" {
            position = Position.absolute
            bottom = (-0.16).vh
            left = 0.vh
            content = "".quoted
            width = 100.pct
            height = 0.16.vh
            backgroundColor = Color.white
            transform {
                scale(0, 1)
            }
            transition("transform", .2.s)
        }
        "header li a:hover::after" {
            transform {
                scale(1, 1)
            }
        }
    }
}