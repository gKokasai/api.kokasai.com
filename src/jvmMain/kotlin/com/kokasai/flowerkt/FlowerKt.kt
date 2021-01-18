package com.kokasai.flowerkt

import com.kokasai.flowerkt.database.*
import com.kokasai.flowerkt.file.*
import com.kokasai.flowerkt.route.*
import com.kokasai.flowerkt.session.*
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.*
import java.time.*

abstract class FlowerKt {
    /**
     * データベース
     */
    abstract val databaseProvider: DatabaseProvider

    /**
     * セッションを保存するデータベース
     */
    open val sessionTable = SessionTable("session", Duration.ofDays(30))

    /**
     * データベースの初期化を行います
     */
    private fun setupDatabase() {
        databaseProvider.connect()
        transaction {
            create(sessionTable)
        }
    }

    /**
     * ファイルの保存先
     */
    abstract val fileProvider: FileProvider

    /**
     * サーバーのポート番号です
     */
    open val port: Int
        get() = 80

    /**
     * Ktor の機能をインストールします
     */
    abstract fun Application.installKtorFeature()

    /**
     * ルートビルダーでルートの登録をします
     */
    abstract val routeBuilder: RouteBuilder

    /**
     * サーバーのルーティングの設定をします
     */
    private fun Routing.setupRouting() {
        routeBuilder.build(this)
    }

    /**
     * モジュールの設定をします
     */
    private fun Application.setupServerModule() {
        installKtorFeature()
        routing {
            setupRouting()
        }
    }

    /**
     * サーバーを起動します
     */
    private fun startServer() {
        embeddedServer(Netty, port) {
            setupServerModule()
        }.start()
    }

    /**
     * プロセスを開始します
     *
     * `main` 関数でこの関数を呼び出してください
     */
    fun launch() {
        setupDatabase()
        startServer()
    }
}