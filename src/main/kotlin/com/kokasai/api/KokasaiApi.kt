package com.kokasai.api

import com.kokasai.api.util.Directory
import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.module.UseExposedDatabaseSQLite
import com.kokasai.flowerkt.module.UseFile
import com.kokasai.flowerkt.module.UseMail
import com.kokasai.flowerkt.module.UseSessionExposedDatabase
import io.ktor.application.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.Logger

interface KokasaiApi : FlowerKt, UseFile, UseSessionExposedDatabase, UseExposedDatabaseSQLite, UseMail {
    companion object : KoinComponent {
        val api by inject<KokasaiApi>()
    }

    val logger: Logger

    override fun installKtor(application: Application) {
        super.installKtor(application)
    }

    override fun launch() {
        GlobalScope.launch { Directory.create() }
        super<UseSessionExposedDatabase>.launch()
        super<FlowerKt>.launch()
    }
}
