object Libraries {
    object GradlePlugin {
        const val application = "org.gradle.application"
        const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
        const val shadow = "com.github.johnrengelman.shadow"
    }

    object Kotlin {
        const val stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val css_jvm = "org.jetbrains:kotlin-css-jvm:${Versions.kotlin_css_jvm}"
    }

    object Ktor {
        const val server_netty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val server_sessions = "io.ktor:ktor-server-sessions:${Versions.ktor}"
        const val auth = "io.ktor:ktor-auth:${Versions.ktor}"
        const val html_builder = "io.ktor:ktor-html-builder:${Versions.ktor}"
        const val gson = "io.ktor:ktor-gson:${Versions.ktor}"
        const val websockets = "io.ktor:ktor-websockets:${Versions.ktor}"
    }

    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
    }

    const val sqlite_driver = "org.xerial:sqlite-jdbc:${Versions.sqlite_driver}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
}