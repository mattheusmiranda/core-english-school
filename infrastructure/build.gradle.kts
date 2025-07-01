plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
        implementation("org.springframework.kafka:spring-kafka:3.1.3")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.6")
        implementation("org.flywaydb:flyway-core:10.13.0")
        implementation("org.flywaydb:flyway-mysql:10.13.0")
        implementation("io.micrometer:micrometer-registry-prometheus:1.12.4")
        implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

        testImplementation(kotlin("test"))
    }

}