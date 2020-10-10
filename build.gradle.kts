plugins {
    id(Libraries.GradlePlugin.application)
    id(Libraries.GradlePlugin.kotlin_multiplatform) version Versions.kotlin
    id(Libraries.GradlePlugin.shadow) version Versions.shadow
}

group = Packages.group
version = Packages.version

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm {
        withJava()
    }

    js {
        browser {
            binaries.executable()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.Kotlin.stdlib_jdk8)
            }
        }

        val jvmMain by getting {
            dependencies {
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
        }

        val jsMain by getting {
            dependencies {

            }
        }
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
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