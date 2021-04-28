import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    kotlin("jvm") version "1.5.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.github.ben-manes.versions") version "0.38.0"
}

group = "com.kokasai.api"
version = "1.0"

allprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }
}

dependencies {
    implementation("io.ktor:ktor-client-okhttp:1.5.3")
    implementation("io.ktor:ktor-auth:1.5.3")
    implementation("io.ktor:ktor-server-netty:1.5.3")
    implementation("io.ktor:ktor-server-sessions:1.5.3")
    implementation("io.ktor:ktor-gson:1.5.3")
    implementation(project(":flowerkt:core"))
    implementation(project(":flowerkt:database-exposed-sqlite"))
    implementation(project(":flowerkt:file-webdav"))
    implementation(project(":flowerkt:session-exposed"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("com.sendgrid:sendgrid-java:4.7.2")
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
