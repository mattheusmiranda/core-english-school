plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.confluent:kafka-avro-serializer:7.5.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation(kotlin("test"))
}

