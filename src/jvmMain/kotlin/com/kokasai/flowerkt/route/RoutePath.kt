package com.kokasai.flowerkt.route

open class RoutePath(val path: String, val full_path: String = path) {
    constructor(parent: RoutePath, path: String): this(path, parent.path + path)
}