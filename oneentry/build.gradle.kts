plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("maven-publish")
}

group = "com.oneentry"
version = "1.2.0"

repositories {
    mavenCentral()
}

publishing {

    publications {
        create<MavenPublication>("bar") {
            groupId = project.group.toString()
            artifactId = "oneentry"
            version = project.version.toString()

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "MyRepo"
            url = uri("https://maven.pkg.github.com/OneEntry/oneentry-kotlin-sdk")
            credentials {
                username = "DinarBes"
                password = "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"
            }
        }
    }
}

kotlin {
    tasks.withType<Jar> {
        if (name.endsWith("sources")) {
            enabled = false
        }
    }
    jvmToolchain(8)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val ktor_version: String by project

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")

    implementation("org.jsoup:jsoup:1.17.2")

    testImplementation(kotlin("test"))
}