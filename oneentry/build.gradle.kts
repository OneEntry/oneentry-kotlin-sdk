plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("maven-publish")
}

val group: String by project
val version: String by project

kotlin {
    tasks.withType<Jar> {
        if (name.endsWith("sources")) {
            enabled = false
        }
    }
}

publishing {

    publications {
        create<MavenPublication>("bar") {
            groupId = project.group.toString()
            artifactId = "oneentry-kotlin"
            version = project.version.toString()

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "OneEntry"
            url = uri("https://maven.pkg.github.com/OneEntry/oneentry-kotlin-sdk")
            credentials {
                username = "DinarBes"
                password = "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"
            }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}

val ktor_version: String by project

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.22.1")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")

    implementation("org.jsoup:jsoup:1.17.2")

    testImplementation(kotlin("test"))
}