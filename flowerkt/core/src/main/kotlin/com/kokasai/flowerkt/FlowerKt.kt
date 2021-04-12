package com.kokasai.flowerkt

import com.kokasai.flowerkt.module.InstallKtorProcess
import com.kokasai.flowerkt.module.LaunchProcess
import com.kokasai.flowerkt.route.RoutePath
import io.ktor.application.Application
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.embeddedServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface FlowerKt : LaunchProcess, InstallKtorProcess {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger("FlowerKt")
    }

    /**
     * サーバーのポート番号です
     */
    val port: Int

    /**
     * ルートの登録
     */
    val routePath: Set<RoutePath>

    /**
     * サーバーのエンジン
     */
    val engine: ApplicationEngineFactory<*, *>

    /**
     * Application::install を実行します
     *
     * installKtor の実行順は継承側で定義する必要があります
     * ```
     * override fun installKtor(application: Application) {
     *     super<Use#A>.installKtor(application)
     *     super<Use#B>.installKtor(application)
     * }
     * ```
     */
    override fun installKtor(application: Application)

    /**
     * サーバーを起動します
     *
     * launch の実行順は継承側で定義する必要があります
     * ```
     * override fun launch() {
     *     super<Use#A>.launch()
     *     super<Use#B>.launch()
     *     super<FlowerKt>.launch()
     * }
     * ```
     *
     * `main` 関数でこの関数を呼び出してください
     */
    override fun launch() {
        embeddedServer(engine, port) {
            installKtor(this)
            routing {
                routePath.forEach {
                    it.build(this)
                }
            }
        }.start()
    }
}
