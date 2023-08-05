plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version "1.4.32"

}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val ktorVersion = "2.0.2"
        val koin = "3.4.0"
        val coroutinesVersion = "1.7.0"
        val sqlDelightVersion = "1.5.5"

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-mock:$ktorVersion")
//                serialization
                api("io.ktor:ktor-client-json:$ktorVersion")
                api("io.ktor:ktor-client-logging:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//                koin(di)
                api("io.insert-koin:koin-core:${koin}")
//                Coroutine
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
//                sqldelight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.ktor:ktor-client-mock:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.0")
                api("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                api("io.insert-koin:koin-android:${koin}")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                api("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "org.dgeek.currencyexchange"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


sqldelight {
    database("AppDatabase") {
        packageName = "org.dgeek.currencyexchange.db"
    }
}