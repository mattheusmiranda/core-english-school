plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.slf4j:slf4j-api:2.0.9")

    testImplementation(kotlin("test"))
}
