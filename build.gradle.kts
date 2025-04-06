plugins {
	kotlin("jvm") version "2.1.10"
}

group = "me.simulatan.htl"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.postgresql:postgresql:42.7.5")

	testImplementation(kotlin("test"))
	val kotestVersion = "5.9.0"

	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.2")

	testImplementation("org.testcontainers:postgresql:1.20.0")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}
