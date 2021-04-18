package com.kokasai.flowerkt.database

import com.kokasai.flowerkt.FlowerKt.Companion.LOGGER
import com.kokasai.flowerkt.file.RemoteFileProvider
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.concurrent.timer

class RemoteSQLiteDatabaseProvider(fileName: String, val fileProvider: RemoteFileProvider, val uploadPeriod: Long) : SQLiteDatabaseProvider(fileName) {
    private val file = File(fileName)

    override fun connect() {
        runBlocking {
            download()
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

    suspend fun download() {
        LOGGER.debug("Start download database file from WebDAV.")
        fileProvider.get(fileName)?.renameTo(file) ?: run {
            LOGGER.warn("Failure download database file from WebDAV.")
        }
    }

    suspend fun upload() {
        if (file.exists()) {
            fileProvider.add(fileName, file)
        } else {
            download()
        }
    }
}
