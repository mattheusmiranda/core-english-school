plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("org.springframework.kafka:spring-kafka:3.1.0")
    implementation("org.flywaydb:flyway-core:9.22.3")
    implementation("io.micrometer:micrometer-registry-prometheus:1.12.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    testImplementation(kotlin("test"))
}