package com.kokasai.the23rd.constants

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.html.*

data class NavigationMenuElement(val href: String, val name: String) {
    constructor(route: RoutePath, name: String): this(route.fullPath, name)

    companion object {
        val list = listOf(
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement(HtmlRoute.Account, "学内の方へ")
        )
    }
}