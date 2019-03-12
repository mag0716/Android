object Version {
    val compileSdk = 28
    val buildTools = "28.0.3"
    val minSdk = 21
    val targetSdk = 28
    val kotlin = "1.3.11"
    val code = 1
    val name = "1.0"
}

object Libraries {
    // Kotlin
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0"
    val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0"

    // basic library
    val appcompat = "androidx.appcompat:appcompat:1.0.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val material = "com.google.android.material:material:1.0.0"

    // Navigation
    val navigationFragment = "android.arch.navigation:navigation-fragment:1.0.0-alpha06"
    val navigationFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:1.0.0-alpha06"
    val navigationUi = "android.arch.navigation:navigation-ui:1.0.0-alpha06"
    val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:1.0.0-alpha06"
    val navigationSafeArgsGradlePlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-alpha06"

    // Third Party
    val retrofit = "com.squareup.retrofit2:retrofit:2.4.0"
    val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:2.4.0"
    val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    val timberCommon = "com.jakewharton.timber:timber-common:5.0.0-SNAPSHOT"
    val timberJdk = "com.jakewharton.timber:timber-jdk:5.0.0-SNAPSHOT"
    val timberAndroid = "com.jakewharton.timber:timber-android:5.0.0-SNAPSHOT"


    // test
    val junit = "junit:junit:4.12"
    val espresso = "androidx.test.espresso:espresso-core:3.1.0-beta01"
    val runner = "androidx.test:runner:1.1.0-beta01"
}