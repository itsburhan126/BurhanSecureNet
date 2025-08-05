plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

group = "com.github.itsburhan126"
version = "v1.0.5" // ✅ Updated version here

android {
    namespace = "com.network.burhansecurenet"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

// ✅ FIXED publishing configuration – no nested afterEvaluate
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.itsburhan126"
            artifactId = "BurhanSecureNet"
            version = "v1.0.5"

            artifact("$buildDir/outputs/aar/${project.name}-release.aar") // ✅ Explicit artifact path
        }
    }
}
