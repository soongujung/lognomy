import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
    kotlin("kapt") version "1.4.10"
//    kotlin("plugin.kapt") version "1.4.10"
}

group = "io.chart"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.querydsl:querydsl-jpa:4.2.1")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch:2.3.4.RELEASE")

    /**
     * spring cloud - open feign (io.github.openfeign)
     * */
    implementation("io.github.openfeign:feign-core:11.0")
    implementation("io.github.openfeign:feign-okhttp:11.0")
    implementation("io.github.openfeign:feign-jackson:11.0")
    implementation("io.github.openfeign:feign-slf4j:11.0")
    implementation("io.github.openfeign:feign-annotation-error-decoder:1.3.0")
//    implementation("io.github.openfeign:feign-ribbon:11.0")

    // -- openfeign in spring cloud (spring boot)
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.5.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-config:2.2.5.RELEASE")

    /**
     * spring cloud - TODO: loadbalancer, circuitBreaker 설정 필요
     */
//    No Feign Client for loadBalancing defined
//    https://stackoverflow.com/questions/63702885/java-lang-illegalstateexception-no-feign-client-for-loadbalancing-defined-did
//    공식 문서 (https://resilience4j.readme.io/docs/getting-started-3) 해보니 안된다. 그래서 한참 헤맸다.

    // -- io.github.resilience4j
//    implementation("io.github.resilience4j:resilience4j-feign:1.5.0")
//    implementation("io.github.resilience4j:resilience4j-core:1.5.0")                  // 이걸 추가하게 되면 다른 모듈과 의존성이 꼬인다.
//    implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.5.0")        // 이걸 추가하게 되면 다른 모듈과 의존성이 꼬인다.
//    implementation("io.github.resilience4j:resilience4j-retry:1.5.0")                 // 이걸 추가하게 되면 다른 모듈과 의존성이 꼬인다.

//     TODO : Ribbon 설정 확인 필요
     // -- spring-boot-starter
//     https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-ribbon
//    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.2.5.RELEASE")

     // -- spring cloud
//    implementation("org.springframework.cloud:spring-cloud-loadbalancer:2.2.5.RELEASE")

    kapt("com.querydsl:querydsl-apt:4.2.2:jpa")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class){
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
