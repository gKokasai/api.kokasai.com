package com.kokasai.flowerkt.route

import io.ktor.routing.Route
import io.ktor.routing.route

open class RoutePath(val path: String, fullPath: String = path, val action: RouteAction? = null) {
    val fullPath = fullPath.replace("//+".toRegex(), "/")

    open val child = setOf<RoutePath>()

    constructor(parent: RoutePath, path: String, action: RouteAction? = null) : this(path, parent.path + "/" + path, action)

    fun build(parent: Route) {
        parent.route(path) {
            action?.invoke(this)
            child.forEach {
                it.build(this)
            }
        }
    }
}
