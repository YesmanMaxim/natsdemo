plugins {
    kotlin("jvm")
}

group = "nats.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.nats:jnats:2.15.0")
}
