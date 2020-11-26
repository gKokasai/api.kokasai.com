package com.kokasai.flowerkt.session

import io.ktor.sessions.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import java.sql.*
import java.time.*

class SessionStorageExposed(private val sessionTable: SessionTable): SessionStorage {
    private fun expireTime(nowTime: Long = Instant.now().toEpochMilli()) =
        nowTime + sessionTable.expireDuration.toMillis()

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
            try {
                ByteReadChannel(sessionTable.select {
                    sessionTable.sessionId eq id
                }.mapNotNull {
                    val lastExpireTime = it[sessionTable.expireTime]
                    val nowTime = Instant.now().toEpochMilli()
                    val expireTime = expireTime(nowTime)
                    when {
                        // セッションの期限切れ
                        lastExpireTime < nowTime -> {
                            sessionTable.deleteWhere { sessionTable.sessionId eq id }
                            null
                        }
                        // 現在時間 と expireTime の差が １日 以上
                        Duration.ofDays(1).toMillis() < (expireTime - lastExpireTime) -> {
                            sessionTable.update(
                                { sessionTable.sessionId eq id },
                                null,
                                { it[sessionTable.expireTime] = expireTime }
                            )
                            it
                        }
                        else -> {
                            it
                        }
                    }
                }.first()[sessionTable.value])
            } catch (ex: NoSuchElementException) {
                commit()
                throw ex
            }
        })
    }

    override suspend fun invalidate(id: String) {
        transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
            sessionTable.deleteWhere { sessionTable.sessionId eq id }
        }
    }
}