plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Micronaut-box"
include("app-simple-micronaut-impl")
include("app-h2-db-connection")
include("app-postgres-db-connection")
include("app-mysql-db-connection")
include("common-config")
include("app-kotlin-implementation")
