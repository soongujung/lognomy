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

    // -- resilience4j
    // -- 참고) https://resilience4j.readme.io/docs/gradle
    implementation("io.github.resilience4j:resilience4j-spring-boot2:1.6.1")
    implementation("io.github.resilience4j:resilience4j-all:1.6.1")
    implementation("io.github.resilience4j:resilience4j-feign:1.6.1")
    // 아래 내용들은 resilience4j-all 의존성으로 모두 처리 (circuitbreaker, retry, ratelimiter)
//    implementation("io.github.resilience4j:resilience4j-circuitbreaker:1.6.1")
//    implementation("io.github.resilience4j:resilience4j-retry:1.6.1")
//    implementation("io.github.resilience4j:resilience4j-ratelimiter:1.6.1")

    // -- starter 에서 버전을 맞춰주거나 권장 자동설정으로 유도하는 feign 설정
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.5.RELEASE")

    // -- starter 에서 버전을 맞춰주거나 권장 자동설정으로 유도하는 spring-cloud 관련 설정
    implementation("org.springframework.cloud:spring-cloud-starter-config:2.2.5.RELEASE")

    // -- 직접 해보니... 아래 설정은 개발자가 직접 맞춰서 하는 것이 더 편하다.
    // load balancing 관련 에러가 발생하고, ribbon 등등 혼동을 주는 에러문구들이 발생한다.
    // spring-cloud-starter가 현재 프로젝트가 기본으로 netflix Feign 을 사용하는 것으로 오해를 하기 때문인것 같다.
//    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:1.0.4.RELEASE")

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
