plugins {
    kotlin("jvm") version "1.3.72"
}

group = "com.gitlab.nitgc.kokasai.the23rd"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-netty:1.3.2")
    implementation("io.ktor:ktor-server-sessions:1.3.2")
    implementation("io.ktor:ktor-auth:1.3.2")
    implementation("io.ktor:ktor-html-builder:1.3.2")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.110-kotlin-1.3.72")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}