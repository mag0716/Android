object Version {
    val compileSdk = 28
    val buildTools = "28.0.3"
    val minSdk = 21
    val targetSdk = 28
    val kotlin = "1.2.71"
    val code = 1
    val name = "1.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    val appcompat = "androidx.appcompat:appcompat:1.0.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

    val material = "com.google.android.material:material:1.0.0"

    val retrofit = "com.squareup.retrofit2:retrofit:2.4.0"

    val junit = "junit:junit:4.12"
    val espresso = "androidx.test.espresso:espresso-core:3.1.0-beta01"
    val runner = "androidx.test:runner:1.1.0-beta01"

}