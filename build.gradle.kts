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
        //binaries.executable()

        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }

        nodejs()
    }

    wasm {
        // To build distributions for and run tests on browser, Node.js or d8 use one:
//        browser() // Browser and Node.js produce error: The top-level-await experiment is not enabled (set experiments.topLevelAwait: true to enabled it)
//        nodejs()
        d8()
    }


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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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

apply(from = File(File("gradle", "scripts"), "publish-codinux.gradle.kts"))
