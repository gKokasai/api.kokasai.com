package com.kokasai.flowerkt

import com.kokasai.flowerkt.database.DatabaseProvider
import com.kokasai.flowerkt.file.FileProvider
import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.flowerkt.session.SessionTable
import io.ktor.application.Application
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

abstract class FlowerKt {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger("FlowerKt")
    }

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
    abstract val port: Int

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
