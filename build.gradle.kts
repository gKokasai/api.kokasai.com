import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    id(Libraries.GradlePlugin.application)
    id(Libraries.GradlePlugin.kotlin_jvm) version Versions.kotlin
    id(Libraries.GradlePlugin.shadow) version Versions.shadow
    id(Libraries.GradlePlugin.ktlint) version Versions.ktlint_gradle
    id(Libraries.GradlePlugin.versions) version Versions.versions
}

group = Packages.group
version = Packages.version

repositories {
    mavenCentral()
    jcenter()
    maven(url = Libraries.FlowerKt.repositoryUrl)
}

dependencies {
    implementation(Libraries.Ktor.client_okhttp)
    implementation(Libraries.Ktor.server_netty)
    implementation(Libraries.Ktor.server_sessions)
    implementation(Libraries.Ktor.gson)
    implementation(Libraries.FlowerKt.auth)
    implementation(Libraries.FlowerKt.core)
    implementation(Libraries.FlowerKt.css)
    implementation(Libraries.FlowerKt.database_sqlite)
    implementation(Libraries.FlowerKt.file_webdav)
    implementation(Libraries.FlowerKt.session_exposed)
    implementation(Libraries.FlowerKt.websocket)
    implementation(Libraries.logback)
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = Packages.jvm_target
        }
    }
}

application {
    mainClassName = Packages.main_class
}

// Heroku で実行されるタスク
task("stage") {
    dependsOn("shadowJar")
}

configure<KtlintExtension> {
    version.set(Versions.ktlint)
}
