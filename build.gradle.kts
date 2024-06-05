import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.flywaydb.flyway") version "10.13.0"
    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.allopen") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

val dbHost = System.getenv("DB_HOST") ?: "localhost"

flyway {
    url = "jdbc:postgresql://$dbHost:5432/catshop"
    user = System.getenv("DB_USERNAME") ?: "postgres"
    password = System.getenv("DB_PASSWORD") ?: "<<YOUR_PASSWORD>>"
}

group = "purr"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("io.github.wimdeblauwe:error-handling-spring-boot-starter:4.2.0")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.hibernate:hibernate-envers:6.2.3.Final")
    implementation("org.flywaydb:flyway-core")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.0")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.getByName<BootRun>("bootRun") {
    environment.put("SPRING_PROFILES_ACTIVE", environment.get("SPRING_PROFILES_ACTIVE") ?: "local")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
