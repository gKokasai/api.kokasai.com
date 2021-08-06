import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
    id("com.github.ben-manes.versions") version "0.39.0"
}

group = "com.kokasai.api"
version = "1.0"

allprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    }
}

dependencies {
    implementation("io.ktor:ktor-client-cio:1.6.1")
    implementation("io.ktor:ktor-auth:1.6.1")
    implementation("io.ktor:ktor-server-netty:1.6.2")
    implementation("io.ktor:ktor-server-sessions:1.6.2")
    implementation("io.ktor:ktor-serialization:1.6.2")
    implementation(project(":flowerkt:core"))
    implementation(project(":flowerkt:database-exposed-sqlite"))
    implementation(project(":flowerkt:file-webdav"))
    implementation(project(":flowerkt:mail-sendgrid"))
    implementation(project(":flowerkt:session-exposed"))
    implementation("ch.qos.logback:logback-classic:1.2.5")
    implementation("io.insert-koin:koin-core:3.1.2")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "com.kokasai.api.LaunchKt"
        )
    }
}

// Heroku で実行されるタスク
task("stage") {
    dependsOn("shadowJar")
}

configure<KtlintExtension> {
    version.set("0.41.0")
}
