package com.kokasai.flowerkt.route

open class RoutePath(val path: String, fullPath: String = path) {
    val fullPath = fullPath.replace("//+".toRegex(), "/")

    constructor(parent: RoutePath, path: String) : this(path, parent.path + "/" + path)
}
