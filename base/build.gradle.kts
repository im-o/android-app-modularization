import dependencies.MyDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-platform-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://www.thecocktaildb.com/api/json/v1/1/\"")
        buildConfigField("String", "BASE_URL_GRAPHQL", "\"https://rickandmortyapi.com/graphql\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.core))

    // DEFAULT DEPENDENCIES
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)
    androidTestImplementation(MyDependencies.espresso_core)
    kapt(MyDependencies.room_compiler)
    testImplementation(MyDependencies.kotlinx_coroutines_test)

    // DI - Hilt
    implementation(MyDependencies.hilt)
    kapt(MyDependencies.hilt_kapt)
}