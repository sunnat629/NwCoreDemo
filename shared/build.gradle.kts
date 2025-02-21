import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDceDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    js {
        moduleName = "shared"
        browser {
            webpackTask {
                output.libraryTarget = "umd"
            }

            useCommonJs()
            commonWebpackConfig {
                outputFileName = "shared.js"
                output?.library = "SharedAppModule"
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport { enabled.set(true) }
                }
            }
            binaries.executable()
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kamel.image)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
//            api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
//            api("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatform
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jsMain.dependencies {
            implementation(libs.kotlinx.html.js)
            implementation(kotlin("stdlib-js"))
            implementation(libs.kotlinx.coroutines.core.js)
            implementation(libs.ktor.client.js)

            implementation(npm("is-sorted", "1.0.5"))        // NPM library
        }
    }
}

tasks.register<Copy>("copyKotlinJsToWeb") {
    from("$buildDir/processedResources/js/main/")
    into("src/jsMain/resources")
    include("*.js")
}

android {
    namespace = "dev.sunnat629.kmm2"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}