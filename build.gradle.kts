import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    application
    kotlin("jvm") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.github.ben-manes.versions") version "0.36.0"
}

group = "com.kokasai.api"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kokasai/maven")
}

dependencies {
    implementation("io.ktor:ktor-client-okhttp:1.5.1")
    implementation("io.ktor:ktor-auth:1.5.1")
    implementation("io.ktor:ktor-server-netty:1.5.1")
    implementation("io.ktor:ktor-server-sessions:1.5.1")
    implementation("io.ktor:ktor-gson:1.5.1")
    implementation("io.ktor:ktor-websockets:1.5.1")
    implementation("com.kokasai.flowerkt:core:0.1.6")
    implementation("com.kokasai.flowerkt:css:0.1.6")
    implementation("com.kokasai.flowerkt:database-exposed-sqlite:0.1.6")
    implementation("com.kokasai.flowerkt:file-webdav:0.1.6")
    implementation("com.kokasai.flowerkt:session-exposed:0.1.6")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

application {
    mainClassName = "com.kokasai.api.LaunchKt"
}

// Heroku で実行されるタスク
task("stage") {
    dependsOn("shadowJar")
}

configure<KtlintExtension> {
    version.set("0.40.0")
}
