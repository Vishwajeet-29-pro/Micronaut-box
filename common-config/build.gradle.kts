plugins {
    id("java")
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