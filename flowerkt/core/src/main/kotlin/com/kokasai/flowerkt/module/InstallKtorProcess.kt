package com.kokasai.flowerkt.module

import io.ktor.application.Application

interface InstallKtorProcess {
    fun installKtor(application: Application)
}
