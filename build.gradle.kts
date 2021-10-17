plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.7")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.7")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:4.0.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    val ENABLE_PREVIEW = "--enable-preview"

    withType<JavaCompile> {
        options.compilerArgs.add(ENABLE_PREVIEW)
        options.compilerArgs.add("-Xlint:preview")
        options.release.set(17)
    }
    withType<Test> {
        useJUnitPlatform()
        jvmArgs?.plusAssign(ENABLE_PREVIEW)
    }
    withType<JavaExec> {
        jvmArgs?.plusAssign(ENABLE_PREVIEW)
    }
}
