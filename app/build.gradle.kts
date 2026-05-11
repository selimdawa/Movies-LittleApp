plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.littleapp.movies"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.littleapp.movies"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.preference.ktx)           //Shared Preference
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Layout
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.cardview)
    //Life Cycle
    implementation(libs.androidx.lifecycle.extensions1)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)   //Navigation Fragment
    implementation(libs.androidx.navigation.ui.ktx)   //Navigation Components
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    //Retrofit
    implementation(libs.retrofit)  //Retrofit
    implementation(libs.converter.gson)  //Gson
    implementation(libs.logging.interceptor)
    //Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)  //Android
    implementation(libs.kotlinx.coroutines.core)  //Core
    //Room
    implementation(libs.androidx.room.ktx)              //Room Kotlin
    implementation(libs.androidx.room.runtime)          //Room
    annotationProcessor(libs.androidx.room.compiler)    //Room Compiler
    kapt(libs.androidx.room.compiler)                   //Room Compiler
    //Needed
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
}