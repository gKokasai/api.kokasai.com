package com.kokasai.api.util.call

import io.ktor.application.ApplicationCall
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.request.receiveMultipart
import java.io.File

suspend fun ApplicationCall.receiveFile(): File? {
    val multiPartData = receiveMultipart()
    val part = multiPartData.readPart() ?: return null
    if (part !is PartData.FileItem) return null
    return File.createTempFile("tmp_", part.originalFileName).apply {
        part.streamProvider().use { inputStream ->
            outputStream().buffered().use {
                inputStream.copyTo(it)
            }
        }
    }
}
