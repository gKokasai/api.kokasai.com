package com.kokasai.flowerkt.database

import com.kokasai.flowerkt.FlowerKt.Companion.LOGGER
import com.kokasai.flowerkt.file.WebDAVFileProvider
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.concurrent.timer

class SQLiteWithWebDAVDatabaseProvider(fileName: String, val webdav: WebDAVFileProvider, val uploadPeriod: Long) : SQLiteDatabaseProvider(fileName) {
    private val file = File(fileName)

    override fun connect() {
        runBlocking {
            webdav.get(fileName)?.renameTo(file) ?: run {
                LOGGER.warn("Failure download database file from WebDAV.")
            }
            Runtime.getRuntime().addShutdownHook(
                Thread {
                    runBlocking {
                        upload()
                    }
                }
            )
            timer(initialDelay = uploadPeriod, period = uploadPeriod) {
                runBlocking {
                    upload()
                }
            }
            super.connect()
        }
    }

    suspend fun upload() {
        webdav.add(fileName, file)
    }
}
