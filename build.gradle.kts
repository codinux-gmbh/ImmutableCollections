plugins {
    kotlin("multiplatform") version "1.8.22"
}

group = "net.codinux.collections"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    // Enable the default target hierarchy:
    targetHierarchy.default()

    jvm {
        jvmToolchain(8)
        withJava()

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }

        nodejs()
    }

//    wasm() // not supported by kotest


    linuxX64()
    mingwX64()

    ios()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchos()
    watchosSimulatorArm64()
    tvos()
    tvosSimulatorArm64()

    
    sourceSets {
        val kotestVersion: String by project

        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                implementation("io.kotest:kotest-assertions-core:$kotestVersion")
            }
        }

        val jvmMain by getting
        val jvmTest by getting

        val jsMain by getting
        val jsTest by getting

        val nativeMain by getting
        val nativeTest by getting
    }
}


ext["projectDescription"] = "Immutable Collections implementations for Kotlin (Multiplatform) like ImmutableList, ImmutableMap, ..."
ext["sourceCodeRepositoryBaseUrl"] = "github.com/codinux-gmbh/ImmutableCollections"

val publishingScript = File(File(project.gradle.gradleUserHomeDir, "scripts"), "publish-codinux.gradle.kts")
if (publishingScript.exists()) {
    apply(from = publishingScript)
}
