plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("native.cocoapods")
    id("maven-publish")
}

group = "dev.subfly.kmpktorcoinbase"
version = "0.0.1-dev"

kotlin {
    cocoapods {
        summary = "Coinbase Shared Module"
        homepage = ""
        version = "0.0.1-dev"
        ios.deploymentTarget = "15.0" // IOS target. Keep as least as possible
        framework {
            baseName = "kmpktorcoinbase"
            isStatic = true
        }
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktor)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "dev.subfly.kmpktorcoinbase"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}
