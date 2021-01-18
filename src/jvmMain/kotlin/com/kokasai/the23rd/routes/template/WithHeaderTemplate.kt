package com.kokasai.the23rd.routes.template

/* ktlint-disable no-wildcard-imports */
import com.kokasai.flowerkt.html.a
import com.kokasai.flowerkt.html.css
import com.kokasai.flowerkt.html.javaScript
import com.kokasai.flowerkt.html.meta
import com.kokasai.the23rd.constants.BASE_COLOR
import com.kokasai.the23rd.constants.DESCRIPTION
import com.kokasai.the23rd.constants.NavigationMenuElement
import com.kokasai.the23rd.constants.SHADOW_COLOR
import com.kokasai.the23rd.constants.THEME_COLOR
import com.kokasai.the23rd.constants.TITLE_NAME
import com.kokasai.the23rd.routes.html.HtmlRoute
import html.tagName.Header
import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import io.ktor.utils.io.charsets.Charset
import kotlinx.css.BorderStyle
import kotlinx.css.BoxSizing
import kotlinx.css.Color
import kotlinx.css.Cursor
import kotlinx.css.Display
import kotlinx.css.FontWeight
import kotlinx.css.LinearDimension
import kotlinx.css.ListStyleType
import kotlinx.css.Position
import kotlinx.css.Visibility
import kotlinx.css.backgroundColor
import kotlinx.css.borderRadius
import kotlinx.css.bottom
import kotlinx.css.boxSizing
import kotlinx.css.color
import kotlinx.css.content
import kotlinx.css.cursor
import kotlinx.css.display
import kotlinx.css.fontFamily
import kotlinx.css.fontSize
import kotlinx.css.fontWeight
import kotlinx.css.height
import kotlinx.css.left
import kotlinx.css.listStyleType
import kotlinx.css.margin
import kotlinx.css.marginLeft
import kotlinx.css.marginRight
import kotlinx.css.opacity
import kotlinx.css.padding
import kotlinx.css.paddingTop
import kotlinx.css.pct
import kotlinx.css.position
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.borderLeft
import kotlinx.css.properties.borderTop
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import kotlinx.css.properties.translateY
import kotlinx.css.px
import kotlinx.css.quoted
import kotlinx.css.ruleSet
import kotlinx.css.textDecoration
import kotlinx.css.top
import kotlinx.css.visibility
import kotlinx.css.width
import kotlinx.css.zIndex
import kotlinx.html.DIV
import kotlinx.html.HTML
import kotlinx.html.UL
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.header
import kotlinx.html.id
import kotlinx.html.lang
import kotlinx.html.li
import kotlinx.html.onTouchStart
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.title
import kotlinx.html.ul

/* ktlint-disable no-wildcard-imports */

class WithHeaderTemplate(
    private val h1: String?,
    private val title_suffix: String? = h1
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
            title("${TITLE_NAME}${if (title_suffix != null) " - $title_suffix" else ""}")
            css(HtmlRoute.Css.Header)
            javaScript(HtmlRoute.Js.MainBundle)
        }
        body {
            header {
                div {
                    id = Header.Id.inner_header
                    p {
                        id = Header.Id.header_title
                        a(href = HtmlRoute.Index) {
                            +TITLE_NAME
                        }
                    }
                    // ハンバーガーメニューアイコン
                    div {
                        id = Header.Id.hamburger_icon
                        span()
                        span()
                        span()
                    }
                    // 横並びメニュー
                    ul {
                        id = Header.Id.horizontal_menu
                        onTouchStart = ""
                        addNavigationElement()
                    }
                }
                // ハンバーガーメニュー
                div {
                    id = Header.Id.hamburger_menu
                    ul {
                        addNavigationElement()
                    }
                }
                div {
                    id = Header.Id.hamburger_background
                }
            }
            div {
                id = Header.Id.inner_body
                if (h1 != null) {
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
            li(Header.Class.menu_element) {
                a(href = href) {
                    +name
                }
            }
        }
    }

    companion object {
        val headerCss = ruleSet {
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
            "#${Header.Id.inner_header}" {
                display = Display.flex
                height = 40.px
                padding(vertical = 8.px)
            }

            // ヘッダータイトル
            "#${Header.Id.header_title}" {
                fontSize = 24.px
                fontWeight = FontWeight.w600
                margin(vertical = LinearDimension.auto)
                padding(top = 4.px, left = 24.px)
            }
            "#${Header.Id.header_title} a" {
                color = BASE_COLOR
                textDecoration = TextDecoration.none
            }

            // ハンバーガーメニュー
            "#${Header.Id.horizontal_menu}" {
                display = Display.none
            }
            "#${Header.Id.hamburger_icon}" {
                position = Position.relative
                margin(LinearDimension.auto)
                marginRight = 24.px
                width = 25.px
                height = 22.px
                cursor = Cursor.pointer
            }
            "#${Header.Id.hamburger_icon} span" {
                position = Position.absolute
                left = 0.px
                width = 100.pct
                height = 2.px
                backgroundColor = BASE_COLOR
                borderRadius = 2.px
            }
            "#${Header.Id.hamburger_icon}, #${Header.Id.hamburger_icon} span" {
                display = Display.inlineBlock
                transition("all", 0.5.s)
                boxSizing = BoxSizing.borderBox
            }
            "#${Header.Id.hamburger_icon} span:nth-of-type(1)" {
                top = 0.px
            }
            "#${Header.Id.hamburger_icon} span:nth-of-type(2)" {
                top = 10.px
            }
            "#${Header.Id.hamburger_icon} span:nth-of-type(3)" {
                bottom = 0.px
            }
            "#${Header.Id.hamburger_menu}" {
                display = Display.none
            }
            ".${Header.Class.active_hamburger} #${Header.Id.hamburger_icon} span:nth-of-type(1)" {
                transform {
                    translateY(10.px)
                    rotate(45.deg)
                }
            }
            ".${Header.Class.active_hamburger} #${Header.Id.hamburger_icon} span:nth-of-type(2)" {
                opacity = 0
            }
            ".${Header.Class.active_hamburger} #${Header.Id.hamburger_icon} span:nth-of-type(3)" {
                transform {
                    translateY((-10).px)
                    rotate((-45).deg)
                }
            }
            "#${Header.Id.hamburger_background}" {
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
            ".${Header.Class.active_hamburger} #${Header.Id.hamburger_background}" {
                opacity = 0.6
                visibility = Visibility.visible
            }
            ".${Header.Class.active_hamburger} #${Header.Id.hamburger_menu}" {
                display = Display.block
            }
            "#${Header.Id.hamburger_menu}" {
                backgroundColor = THEME_COLOR
                width = 100.pct - 16.px
                marginLeft = 8.px
                padding(vertical = 8.px)
                borderTop(2.4.px, BorderStyle.dotted, BASE_COLOR)
            }
            "#${Header.Id.hamburger_menu} ul" {
                padding(8.px, 24.px)
            }
            "#${Header.Id.hamburger_menu} .${Header.Class.menu_element}+ .${Header.Class.menu_element}" {
                padding(vertical = 4.px)
            }
            "#${Header.Id.hamburger_menu} .${Header.Class.menu_element} a" {
                display = Display.block
                fontSize = 20.px
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
            }

            // インナーボディー
            "#${Header.Id.inner_body}" {
                width = 100.pct - 16.px
                margin(vertical = 8.px, horizontal = LinearDimension.auto)
            }
            media("screen and (min-width: 1040px)") {
                "#${Header.Id.inner_body}" {
                    width = 1024.px
                }
            }

            media("screen and (min-width: 769px)") {
                // 横並びメニュー
                "#${Header.Id.hamburger_icon}" {
                    display = Display.none
                }
                "#${Header.Id.horizontal_menu}" {
                    display = Display.block
                    fontSize = 20.px
                    margin(LinearDimension.auto)
                    marginRight = 0.px
                    paddingTop = 2.4.px
                }
                "#${Header.Id.horizontal_menu} .${Header.Class.menu_element}" {
                    padding(horizontal = 24.px)
                    display = Display.inline
                }
                "#${Header.Id.horizontal_menu} .${Header.Class.menu_element}+ .${Header.Class.menu_element}" {
                    borderLeft(2.4.px, BorderStyle.solid, BASE_COLOR)
                }
                "#${Header.Id.horizontal_menu} .${Header.Class.menu_element} a" {
                    fontWeight = FontWeight.w500
                    color = BASE_COLOR
                    textDecoration = TextDecoration.none
                    position = Position.relative
                }
                "#${Header.Id.horizontal_menu} .${Header.Class.menu_element} a::after" {
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
                "#${Header.Id.horizontal_menu} .${Header.Class.menu_element} a:hover::after" {
                    transform {
                        scale(1, 1)
                    }
                }
            }
        }
    }
}
