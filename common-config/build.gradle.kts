plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
}

group = "com.micronaut.config"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {}

tasks.test {
    useJUnitPlatform()
}