package com.gitlab.nitgc.kokasai.flowerkt.session

import org.jetbrains.exposed.sql.*
import java.time.*

open class SessionTable(name: String, val expireDuration: Duration): Table(name) {
    val sessionId = text("sessionId").uniqueIndex()
    val value = text("value")
    val expireTime = long("expireTime")

    override val primaryKey = PrimaryKey(sessionId)
}