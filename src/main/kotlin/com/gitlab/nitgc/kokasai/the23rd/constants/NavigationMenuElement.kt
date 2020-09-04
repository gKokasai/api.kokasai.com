package com.gitlab.nitgc.kokasai.the23rd.constants

data class NavigationMenuElement(val href: String, val name: String) {
    constructor(route: RoutePath, name: String): this(route.fullpath, name)

    companion object {
        val list = listOf(
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement(HtmlRoutes.Account, "学内の方へ")
        )
    }
}