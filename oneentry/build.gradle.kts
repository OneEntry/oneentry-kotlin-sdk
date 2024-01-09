plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("maven-publish")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val ktor_version: String by project

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")

    implementation("org.jsoup:jsoup:1.16.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

