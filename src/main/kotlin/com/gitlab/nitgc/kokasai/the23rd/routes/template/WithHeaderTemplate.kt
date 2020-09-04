package com.gitlab.nitgc.kokasai.the23rd.routes.template

import com.gitlab.nitgc.kokasai.the23rd.constants.BASE_COLOR
import com.gitlab.nitgc.kokasai.the23rd.constants.DESCRIPTION
import com.gitlab.nitgc.kokasai.the23rd.constants.HtmlRoutes
import com.gitlab.nitgc.kokasai.the23rd.constants.NavigationMenuElement
import com.gitlab.nitgc.kokasai.the23rd.constants.SHADOW_COLOR
import com.gitlab.nitgc.kokasai.the23rd.constants.THEME_COLOR
import com.gitlab.nitgc.kokasai.the23rd.constants.TITLE_NAME
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.extension.meta
import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import io.ktor.utils.io.charsets.Charset
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

class WithHeaderTemplate(
    private val h1: String?,
    private val title_suffix: String? = h1,
    private val javaScripts: Iterable<HtmlRoutes.Js>? = null
) : Template<HTML> {
    val body = Placeholder<DIV>()

    override fun HTML.apply() {
        lang = "ja"
        head {
            meta {
                charset = Charset.defaultCharset().toString()
                description = DESCRIPTION
                viewport = "width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"
            }
            title("${TITLE_NAME}${if(title_suffix != null) " - $title_suffix" else ""}")
            css(HtmlRoutes.Css.Header)
            javaScript("https://code.jquery.com/jquery-3.5.1.min.js")
            javaScript(HtmlRoutes.Js.Header)
            javaScripts?.forEach { javaScript(it) }
        }
        body {
            header {
                div {
                    id = "inner_header"
                    p {
                        id = "header_title"
                        a(href = HtmlRoutes.Home) {
                            +TITLE_NAME
                        }
                    }
                    // ハンバーガーメニューアイコン
                    div {
                        id = "hamburger_icon"
                        span()
                        span()
                        span()
                    }
                    // 横並びメニュー
                    ul {
                        id = "horizontal_menu"
                        onTouchStart = ""
                        addNavigationElement()
                    }
                }
                // ハンバーガーメニュー
                div {
                    id = "hamburger_menu"
                    ul {
                        addNavigationElement()
                    }
                }
                div {
                    id = "hamburger_background"
                }
            }
            div {
                id = "inner_body"
                if(h1 != null) {
                    h1 {
                        +h1
                    }
                }
                insert(body)
            }
        }
    }

    private fun UL.addNavigationElement() {
        NavigationMenuElement.list.forEach { (href, name) ->
            li("menu_element") {
                a(href = href) {
                    +name
                }
            }
        }
    }


    companion object {
        val headerCss: CSSBuilder.() -> Unit = {
            "*" {
                margin(0.px)
                padding(0.px)
            }
            "body" {
                backgroundColor = BASE_COLOR
                fontFamily = "sans-serif"
            }
            "li" {
                listStyleType = ListStyleType.none
            }

            // ヘッダー
            "header" {
                backgroundColor = THEME_COLOR
                boxShadow(SHADOW_COLOR.withAlpha(0.4), offsetY = 1.6.px, blurRadius = 3.2.px)
            }
            "#inner_header" {
                display = Display.flex
                height = 40.px
                padding(vertical = 8.px)
            }

            // ヘッダータイトル
            "#header_title" {
                fontSize = 24.px
                fontWeight = FontWeight.w600
                margin(vertical = LinearDimension.auto)
                padding(top = 4.px, left = 24.px)
            }
            "#header_title a" {
                color = BASE_COLOR
                textDecoration = TextDecoration.none
            }

            // ハンバーガーメニュー
            "#horizontal_menu" {
                display = Display.none
            }
            "#hamburger_icon" {
                position = Position.relative
                margin(LinearDimension.auto)
                marginRight = 24.px
                width = 25.px
                height = 22.px
                cursor = Cursor.pointer
            }
            "#hamburger_icon span" {
                position = Position.absolute
                left = 0.px
                width = 100.pct
                height = 2.px
                backgroundColor = BASE_COLOR
                borderRadius = 2.px
            }
            "#hamburger_icon, #hamburger_icon span" {
                display = Display.inlineBlock
                transition("all", 0.5.s)
                boxSizing = BoxSizing.borderBox
            }
            "#hamburger_icon span:nth-of-type(1)" {
                top = 0.px
            }
            "#hamburger_icon span:nth-of-type(2)" {
                top = 10.px
            }
            "#hamburger_icon span:nth-of-type(3)" {
                bottom = 0.px
            }
            "#hamburger_menu" {
                display = Display.none
            }
            ".active_hamburger #hamburger_icon span:nth-of-type(1)" {
                transform {
                    translateY(10.px)
                    rotate(45.deg)
                }
            }
            ".active_hamburger #hamburger_icon span:nth-of-type(2)" {
                opacity = 0
            }
            ".active_hamburger #hamburger_icon span:nth-of-type(3)" {
                transform {
                    translateY((-10).px)
                    rotate((-45).deg)
                }
            }
            "#hamburger_background" {
                position = Position.fixed
                left = 0.px
                top = 0.px
                width = 100.pct
                height = 100.pct
                zIndex = -1
                backgroundColor = Color.black
                opacity = 0
                visibility = Visibility.hidden
                transition("all", .6.s)
                cursor = Cursor.pointer
            }
            ".active_hamburger #hamburger_background" {
                opacity = 0.6
                visibility = Visibility.visible
            }
            ".active_hamburger #hamburger_menu" {
                display = Display.block
            }
            "#hamburger_menu" {
                backgroundColor = THEME_COLOR
                width = 100.pct - 16.px
                marginLeft = 8.px
                padding(vertical = 8.px)
                borderTop(2.4.px, BorderStyle.dotted, BASE_COLOR)
            }
            "#hamburger_menu ul" {
                padding(8.px, 24.px)
            }
            "#hamburger_menu .menu_element+ .menu_element" {
                padding(vertical = 4.px)
            }
            "#hamburger_menu .menu_element a" {
                display = Display.block
                fontSize = 20.px
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
            }

            // インナーボディー
            "#inner_body" {
                width = 100.pct - 16.px
                margin(vertical = 8.px, horizontal = LinearDimension.auto)
            }
            media("screen and (min-width: 1040px)") {
                "#inner_body" {
                    width = 1024.px
                }
            }

            media("screen and (min-width: 769px)") {
                // 横並びメニュー
                "#hamburger_icon" {
                    display = Display.none
                }
                "#horizontal_menu" {
                    display = Display.block
                    fontSize = 20.px
                    margin(LinearDimension.auto)
                    marginRight = 0.px
                    paddingTop = 2.4.px
                }
                "#horizontal_menu .menu_element" {
                    padding(horizontal = 24.px)
                    display = Display.inline
                }
                "#horizontal_menu .menu_element+ .menu_element" {
                    borderLeft(2.4.px, BorderStyle.solid, BASE_COLOR)
                }
                "#horizontal_menu .menu_element a" {
                    fontWeight = FontWeight.w500
                    color = BASE_COLOR
                    textDecoration = TextDecoration.none
                    position = Position.relative
                }
                "#horizontal_menu .menu_element a::after" {
                    position = Position.absolute
                    bottom = (-1.6).px
                    left = 0.px
                    content = "".quoted
                    width = 100.pct
                    borderTop(2.4.px, BorderStyle.solid, BASE_COLOR)
                    transform {
                        scale(0, 1)
                    }
                    transition("transform", .2.s)
                }
                "#horizontal_menu .menu_element a:hover::after" {
                    transform {
                        scale(1, 1)
                    }
                }
            }
        }
    }
}