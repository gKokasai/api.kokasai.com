package com.gitlab.nitgc.kokasai.flowerkt

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.*
import java.time.*

interface FlowerKt {
    /**
     * データベースのURL
     */
    val databaseUrl: String

    /**
     * セッションを保存するデータベース
     */
    val sessionTable: SessionTable
        get() = SessionTable("session", Duration.ofDays(30))

    /**
     * データベースの初期化を行います
     */
    open fun setupDatabase() {
        Database.connect(databaseUrl)
        transaction {
            create(sessionTable)
        }
    }

    fun launch() {
        setupDatabase()
    }
}