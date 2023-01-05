group = "com.tsubaki"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_19

val dbUrl: String by project
val dbUser: String by project
val dbPassword: String by project
val dbLocation: String by project
val springBootStarterVersion: String by project
val springBootStarterDataJpaVersion: String by project
val springBootStarterSecurityVersion: String by project
val jjwtApiVersion: String by project
val springBootStarterValidationVersion: String by project
val springBootStarterWebVersion: String by project
val flywayCoreVersion: String by project
val servletApiVersion: String by project
val postgresqlVersion: String by project
val jjwtImplVersion: String by project
val jjwtJacksonVersion: String by project
val lombokVersion: String by project
val springBootStarterTestVersion: String by project

plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.flywaydb.flyway") version "9.10.2"
}

flyway {
    url = dbUrl
    user = dbUser
    password = dbPassword
    baselineOnMigrate = true
}

repositories{
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:${springBootStarterVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootStarterDataJpaVersion}")
    implementation("org.springframework.boot:spring-boot-starter-security:${springBootStarterSecurityVersion}")
    implementation("io.jsonwebtoken:jjwt-api:${jjwtApiVersion}")
    implementation("org.springframework.boot:spring-boot-starter-validation:${springBootStarterValidationVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootStarterWebVersion}")
    implementation("org.flywaydb:flyway-core:${flywayCoreVersion}")
    implementation("jakarta.servlet:jakarta.servlet-api:${servletApiVersion}")
    runtimeOnly("org.postgresql:postgresql:${postgresqlVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${jjwtImplVersion}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${jjwtJacksonVersion}")
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springBootStarterTestVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
