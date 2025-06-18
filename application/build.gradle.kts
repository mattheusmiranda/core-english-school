plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation(kotlin("test"))
}
