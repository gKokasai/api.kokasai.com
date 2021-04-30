package com.kokasai.api.local

import com.kokasai.api.KokasaiApi
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

fun main() {
    KokasaiApiImpl().also { api ->
        startKoin {
            val module = module {
                single<KokasaiApi> { api }
            }
            modules(module)
        }
    }.launch()
}
