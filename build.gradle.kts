@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl


plugins {
    kotlin("multiplatform") version "1.9.25"
}

group = "net.codinux.collections"
version = "1.5.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)

    jvm {
        withJava()

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    js(IR) {
        moduleName = "immutable-collections"
        binaries.executable()

        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            }
        }


        nodejs()
    }

    wasmJs {
        browser {
            commonWebpackConfig {
                experiments.add("topLevelAwait")
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            }

            // Uncomment the next line to apply Binaryen and get optimized wasm binaries
            applyBinaryen()
        }

        //nodejs() // WASM Node.js does currently not work
    }


    linuxX64()
    mingwX64()


    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchosArm64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosSimulatorArm64()

    applyDefaultHierarchyTemplate()

    
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


ext["customArtifactId"] = "immutable-collections"
ext["projectDescription"] = "Immutable Collections implementations for Kotlin (Multiplatform) like ImmutableList, ImmutableMap, ..."
ext["sourceCodeRepositoryBaseUrl"] = "github.com/codinux-gmbh/ImmutableCollections"

apply(from = File(File("gradle", "scripts"), "publish-codinux.gradle.kts"))
