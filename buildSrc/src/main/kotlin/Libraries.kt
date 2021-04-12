object Libraries {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
        const val css_jvm = "org.jetbrains:kotlin-css-jvm:${Version.kotlin_css_jvm}"
    }

    object Ktor {
        const val server_host_common = "io.ktor:ktor-server-host-common:${Version.ktor}"
        const val client_core = "io.ktor:ktor-client-core:${Version.ktor}"
        const val client_auth = "io.ktor:ktor-client-auth:${Version.ktor}"
        const val html_builder = "io.ktor:ktor-html-builder:${Version.ktor}"
    }

    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Version.exposed}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Version.exposed}"
    }

    const val sqlite_driver = "org.xerial:sqlite-jdbc:${Version.sqlite_driver}"
}
