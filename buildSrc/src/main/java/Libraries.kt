object Libraries {
    object GradlePlugin {
        const val application = "org.gradle.application"
        const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
        const val shadow = "com.github.johnrengelman.shadow"
        const val ktlint = "org.jlleitschuh.gradle.ktlint"
        const val versions = "com.github.ben-manes.versions"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
    }

    object Ktor {
        const val client_okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val server_netty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val server_sessions = "io.ktor:ktor-server-sessions:${Versions.ktor}"
        const val gson = "io.ktor:ktor-gson:${Versions.ktor}"
    }

    object FlowerKt {
        const val repositoryUrl = "https://dl.bintray.com/kokasai/maven"
        const val auth = "com.kokasai.flowerkt:auth:${Versions.flowerkt}"
        const val core = "com.kokasai.flowerkt:core:${Versions.flowerkt}"
        const val css = "com.kokasai.flowerkt:css:${Versions.flowerkt}"
        const val database_sqlite = "com.kokasai.flowerkt:database-exposed-sqlite:${Versions.flowerkt}"
        const val file_webdav = "com.kokasai.flowerkt:file-webdav:${Versions.flowerkt}"
        const val session_exposed = "com.kokasai.flowerkt:session-exposed:${Versions.flowerkt}"
        const val websocket = "com.kokasai.flowerkt:websocket:${Versions.flowerkt}"
    }

    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
}
