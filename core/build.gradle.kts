plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.kevinfre.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "KEY", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YTRiMTBmMDM0YzcxYjliZTc3YTczNDNiNjQ2Nzg1MCIsInN1YiI6IjY1NTJmZmNmZDRmZTA0MDBmZTA0ZjI2OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.N8qcQ0EmvhSDLAlto7DjRUIW6SI2G8s7HGvZCo8rJCI\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")
    androidTestImplementation ("androidx.room:room-testing:2.6.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("androidx.room:room-ktx:2.6.1")
    api ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation ("net.zetetic:android-database-sqlcipher:4.5.3")
    implementation ("androidx.sqlite:sqlite-ktx:2.4.0")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.13")

    testImplementation ("org.mockito:mockito-core:5.6.0")
    testImplementation ("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}