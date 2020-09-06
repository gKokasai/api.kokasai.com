plugins {
    id(Libraries.GradlePlugin.application)
    id(Libraries.GradlePlugin.kotlin_jvm) version Versions.kotlin
    id(Libraries.GradlePlugin.shadow) version Versions.shadow
}

group = Packages.group
version = Packages.version

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(Libraries.Kotlin.stdlib_jdk8)
    implementation(Libraries.Ktor.server_netty)
    implementation(Libraries.Ktor.server_sessions)
    implementation(Libraries.Ktor.auth)
    implementation(Libraries.Ktor.html_builder)
    implementation(Libraries.Ktor.gson)
    implementation(Libraries.Ktor.websockets)
    implementation(Libraries.Kotlin.css_jvm)
    implementation(Libraries.Exposed.core)
    implementation(Libraries.Exposed.jdbc)
    implementation(Libraries.sqlite_driver)
    implementation(Libraries.logback)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = Packages.jvm_target
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = Packages.jvm_target
    }
}

application {
    mainClassName = Packages.main_class
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to Packages.main_class
            )
        )
    }
}