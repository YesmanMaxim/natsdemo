plugins {
    kotlin("jvm")
}

group = "nats.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":library"))
    implementation("io.nats:jnats:2.15.0")
}
