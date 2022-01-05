/**
 * Created by rivaldy on 04/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

object MyDependencies {

    // DEFAULT DEPENDENCIES
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.app_compat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val junit = "junit:junit:${Versions.junit_version}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.junit_test_version}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_test_version}"

    //UI
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout_version}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.ui_ktx_version}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.ui_ktx_version}"

    // REMOTE
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit2_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofit2_adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3_version}"

    // Coroutines
    const val kotlinx_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines_version}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"

    // Lifecycle KTX
    const val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_extensions_version}"

    // Activity & Fragment KTX
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_gradle_plugin}"
    const val hilt_kapt = "com.google.dagger:hilt-compiler:${Versions.hilt_gradle_plugin}"

    // ViewModel with Hilt
    const val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewModels}"
}