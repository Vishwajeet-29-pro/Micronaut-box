plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.25" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.25" apply false
    id("com.google.devtools.ksp") version "2.1.10-1.0.29" apply false
}

repositories {
    mavenCentral()
}