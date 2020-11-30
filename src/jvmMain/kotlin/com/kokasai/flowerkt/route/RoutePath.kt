package com.kokasai.flowerkt.route

open class RoutePath(val path: String, val fullPath: String = path) {
    constructor(parent: RoutePath, path: String): this(path, parent.path + path)
}