plugins {
    application
    kotlin("jvm") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "com.gitlab.nitgc.kokasai.the23rd"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    val ktorVersion = "1.4.0"

    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.110-kotlin-1.4.0")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}