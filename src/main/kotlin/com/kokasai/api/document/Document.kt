package com.kokasai.api.document

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.util.Directory
import java.io.File

object Document {
    suspend fun post(name: String, file: File) = api.fileProvider.add("${Directory.document}/$name", file)

    suspend fun get(name: String) = api.fileProvider.get("${Directory.document}/$name")

    suspend fun list() = api.fileProvider.list(Directory.document).orEmpty()
}
