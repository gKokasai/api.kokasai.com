package com.kokasai.flowerkt.file

import java.io.*

interface FileProvider {
    fun add(path: String, file: File)
    fun remove(path: String)
    fun get(path: String)
}