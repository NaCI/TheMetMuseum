import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // kapt
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // App libraries
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    const val lifecycleCommon =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val okhttpLogging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"

    // Android Test libraries
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.espressoVersion}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    const val coroutinesTesting =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    // Test libraries
    const val junit = "junit:junit:${Versions.junitVersion}"

    val kaptLibraries = arrayListOf<String>().apply {
        add(glideCompiler)
        add(hiltCompiler)
    }

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appCompat)
        add(constraintLayout)
        add(swipeRefreshLayout)
        add(fragmentKtx)
        add(lifecycleCommon)
        add(lifecycleViewModel)
        add(lifecycleRuntime)
        add(recyclerview)
        add(glide)
        add(materialDesign)
        add(gson)
        add(okhttpLogging)
        add(gsonConverter)
        add(retrofit)
        add(coroutinesAndroid)
        add(coroutinesCore)
        add(hilt)
        add(timber)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(coreTesting)
        add(espressoContrib)
        add(espressoIntents)
        add(hiltTesting)
        add(coroutinesTesting)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}