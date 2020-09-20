package com.gitlab.nitgc.kokasai.the23rd.extension

import io.ktor.sessions.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import java.sql.*
import java.time.*

open class SessionTable(name: String, val expireDuration: Duration) : Table(name) {
    val sessionId = text("sessionId").uniqueIndex()
    val value = text("value")
    val expireTime = long("expireTime")

    override val primaryKey = PrimaryKey(sessionId)
}

class SessionStorageExposed(private val sessionTable: SessionTable): SessionStorage {
    private fun expireTime() = Instant.now().toEpochMilli() + sessionTable.expireDuration.toMillis()

    override suspend fun write(id: String, provider: suspend (ByteWriteChannel) -> Unit) {
        coroutineScope {
            val channelToByteArray = writer(Dispatchers.Unconfined, autoFlush = true) {
                provider(channel)
            }.channel.toByteArray()

            transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
                sessionTable.insertIgnore {
                    it[sessionId] = id
                    it[value] = channelToByteArray.toString(Charsets.UTF_8)
                    it[expireTime] = expireTime()
                }
            }
        }
    }

    override suspend fun <R> read(id: String, consumer: suspend (ByteReadChannel) -> R): R {
        return consumer(transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
            ByteReadChannel(sessionTable.select {
                sessionTable.sessionId eq id
            }.first().apply {
                sessionTable.update(
                    { sessionTable.sessionId eq id },
                    null,
                    { it[sessionTable.expireTime] = expireTime() }
                )
            }[sessionTable.value])
        })
    }

    override suspend fun invalidate(id: String) {
        transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
            sessionTable.deleteWhere { sessionTable.sessionId eq id }
        }
    }
}