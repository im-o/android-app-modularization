import dependencies.MyDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-platform-android")
    id("com.apollographql.apollo3").version(Versions.apollo_version)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://www.thecocktaildb.com/api/json/v1/1/\"")
            buildConfigField("String", "BASE_URL_GRAPHQL", "\"https://rickandmortyapi.com/graphql\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://www.thecocktaildb.com/api/json/v1/1/\"")
            buildConfigField("String", "BASE_URL_GRAPHQL", "\"https://rickandmortyapi.com/graphql\"")
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
    apollo {
        packageName.set("com.rivaldy.id.core")
    }
}

dependencies {

    // DEFAULT DEPENDENCIES
    api(MyDependencies.core_ktx)
    api(MyDependencies.appcompat)
    api(MyDependencies.material)
    api(MyDependencies.legacy_support)
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)
    androidTestImplementation(MyDependencies.espresso_core)

    // UI
    api(MyDependencies.swiperefreshlayout)
    api(MyDependencies.constraint_layout)
    api(MyDependencies.ui_ktx)
    api(MyDependencies.navigation_fragment_ktx)

    // REMOTE
    api(MyDependencies.retrofit)
    api(MyDependencies.retrofit2_converter_gson)
    api(MyDependencies.retrofit2_adapter_rxjava2)
    api(MyDependencies.okhttp3)

    // Coroutines
    testImplementation(MyDependencies.kotlinx_coroutines_test)

    // Glide
    api(MyDependencies.glide)

    // Lifecycle KTX
    api(MyDependencies.lifecycle_extensions)
    // Activity & Fragment KTX
    api(MyDependencies.activity_ktx)
    api(MyDependencies.fragment_ktx)

    // DI - Hilt
    implementation(MyDependencies.hilt)
    kapt(MyDependencies.hilt_kapt)

    // ViewModel with Hilt
    api(MyDependencies.hilt_viewmodel)

    // Apollo Client
    api(MyDependencies.apollo_client)
}