package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

object WithHeaderTemplate : Template<HTML> {
    val body = Placeholder<BODY>()

    override fun HTML.apply() {
        head {
            link(rel = "stylesheet", href = "/header.css", type = "text/css")
            script(src = "https://code.jquery.com/jquery-3.5.1.min.js", type = "text/javascript") {}
            script {
                @Suppress("DEPRECATION")
                +"""
                    $(function(){
                        $('#hamburger_menu').on('click', function() {
                            $(this).toggleClass('active');
                            return false;
                        });
                    });
                """.trimIndent()
            }
        }
        body {
            header {
                h1 {
                    a(href = "/") {
                        +"工華祭"
                    }
                }
                // ハンバーガーメニュー
                div {
                    id = "hamburger_menu"
                    span()
                    span()
                    span()
                }
                // 横並びメニュー
                ul {
                    onTouchStart = ""
                    menu("#", "ページ")
                    menu("#", "ページ")
                    menu("/account", "学内の方へ")
                }
            }
            insert(body)
        }
    }

    private fun UL.menu(href: String, name: String) {
        li {
            a(href = href) {
                +name
            }
        }
    }

    val headerCss: CSSBuilder.() -> Unit = {
        val useHamburgerAspectRatio = "27/50"

        "body" {
            backgroundColor = BASE_COLOR
            margin(0.vh)
        }
        "li" {
            listStyleType = ListStyleType.none
        }
        "header" {
            display = Display.flex
            backgroundColor = THEME_COLOR
            padding(vertical = max(0.8.vh, 8.px))
            boxShadow(SHADOW_COLOR.withAlpha(0.4), offsetY = 0.16.vh, blurRadius = 0.32.vh)
        }
        "header h1" {
            fontSize = max(3.2.vh, 32.px)
            margin(vertical = LinearDimension.auto)
            paddingTop = 0.32.vh
            paddingLeft = 2.24.vh
        }
        "header h1 a" {
            color = BASE_COLOR
            textDecoration = TextDecoration.none
        }
        // ハンバーガーメニューを使用
        media("(max-aspect-ratio: $useHamburgerAspectRatio)") {
            "header ul" {
                display = Display.none
            }
            "#hamburger_menu" {
                position = Position.relative
                margin(LinearDimension.auto)
                marginRight = 2.24.vh
                width = 50.px
                height = 44.px
                cursor = Cursor.pointer
            }
            "#hamburger_menu span" {
                position = Position.absolute
                left = 0.px
                width = 100.pct
                height = 4.px
                backgroundColor = BASE_COLOR
                borderRadius = 4.px
            }
            "#hamburger_menu, #hamburger_menu span" {
                display = Display.inlineBlock
                transition("all", 0.5.s)
                boxSizing = BoxSizing.borderBox
            }
            "#hamburger_menu span:nth-of-type(1)" {
                top = 0.px
            }
            "#hamburger_menu span:nth-of-type(2)" {
                top = 20.px
            }
            "#hamburger_menu span:nth-of-type(3)" {
                bottom = 0.px
            }
            "#hamburger_menu.active span:nth-of-type(1)" {
                transform {
                    translateY(20.px)
                    rotate(45.deg)
                }
            }
            "#hamburger_menu.active span:nth-of-type(2)" {
                opacity = 0
            }
            "#hamburger_menu.active span:nth-of-type(3)" {
                transform {
                    translateY((-20).px)
                    rotate((-45).deg)
                }
            }
        }
        // 横並びメニューを使用
        media("(min-aspect-ratio: $useHamburgerAspectRatio)") {
            "#hamburger_menu" {
                display = Display.none
            }
            "header ul" {
                fontSize = max(2.24.vh, 24.px)
                margin(LinearDimension.auto)
                marginRight = 0.vh
                paddingTop = 0.224.vh
            }
            "header li" {
                padding(0.vh, 2.24.vh)
                display = Display.inline
            }
            "header li+ li" {
                borderLeft(0.16.vh, BorderStyle.solid, BASE_COLOR)
            }
            "header li a" {
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
                position = Position.relative
            }
            "header li a::after" {
                position = Position.absolute
                bottom = (-0.16).vh
                left = 0.vh
                content = "".quoted
                width = 100.pct
                height = 0.16.vh
                backgroundColor = BASE_COLOR
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
}