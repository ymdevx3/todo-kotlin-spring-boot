import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	idea
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
	war
}

apply {
	plugin("kotlin")
	plugin("war")
}

group = "org.ymdev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

extra["azureVersion"] = "2.1.6"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	compile("org.springframework.boot:spring-boot-starter-thymeleaf")
	compile("org.springframework.boot:spring-boot-devtools")
	compile("org.springframework.boot:spring-boot-starter-jdbc")
	runtime("com.h2database:h2")
	
	testCompile("org.assertj:assertj-core:3.9.0")

	implementation("com.microsoft.azure:azure-spring-boot-starter")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}

dependencyManagement {
	imports {
		mavenBom("com.microsoft.azure:azure-spring-boot-bom:${property("azureVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
