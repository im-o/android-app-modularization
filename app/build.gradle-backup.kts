import dependencies.MyDependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-platform-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        applicationId = "com.rivaldy.id.cocktail"
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        versionCode = Versions.version_code
        versionName = Versions.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

//    dynamicFeatures = mutableSetOf(
//        Modules.testFeature
//    )
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(project(Modules.base))
//    api(project(Modules.testFeature))

    // DEFAULT DEPENDENCIES
    implementation(MyDependencies.core_ktx)
    implementation(MyDependencies.appcompat)
    implementation(MyDependencies.material)
    implementation(MyDependencies.legacy_support)
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)
    androidTestImplementation(MyDependencies.espresso_core)

    // UI
    implementation(MyDependencies.swiperefreshlayout)
    implementation(MyDependencies.constraint_layout)
    implementation(MyDependencies.ui_ktx)
    implementation(MyDependencies.navigation_fragment_ktx)

    // REMOTE
    implementation(MyDependencies.retrofit)
    implementation(MyDependencies.retrofit2_converter_gson)
    implementation(MyDependencies.retrofit2_adapter_rxjava2)
    implementation(MyDependencies.okhttp3)

    // Coroutines
    testImplementation(MyDependencies.kotlinx_coroutines_test)

    // Glide
    implementation(MyDependencies.glide)

    // Lifecycle KTX
    implementation(MyDependencies.lifecycle_extensions)
    // Activity & Fragment KTX
    implementation(MyDependencies.activity_ktx)
    implementation(MyDependencies.fragment_ktx)

    // DI - Hilt
    implementation(MyDependencies.hilt)
    kapt(MyDependencies.hilt_kapt)

    // ViewModel with Hilt
    implementation(MyDependencies.hilt_viewmodel)
}