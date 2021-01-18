package com.kokasai.the23rd.constants

import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.the23rd.routes.html.HtmlRoute

data class NavigationMenuElement(val href: String, val name: String) {
    constructor(route: RoutePath, name: String) : this(route.fullPath, name)

    companion object {
        val list = listOf(
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement(HtmlRoute.Account, "学内の方へ")
        )
    }
}
