import com.android.build.gradle.AppExtension
import org.gradle.kotlin.dsl.kotlin
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.ReporterType

apply {
    plugin("android")
    plugin("kotlin-android")
    plugin("kotlin-android-extensions")
    plugins.apply("org.jlleitschuh.gradle.ktlint")
}
configure<AppExtension> {
    compileSdkVersion(26)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(26)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    //"implementation"(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.0")
    "implementation"("com.android.support:appcompat-v7:26.1.0")
    "implementation"("com.android.support:support-v4:26.1.0")
    "implementation"("com.android.support:recyclerview-v7:26.1.0")
    "implementation"("com.android.support:design:26.1.0")
    "implementation"("com.android.support.constraint:constraint-layout:1.0.2")
    "testImplementation"("junit:junit:4.12")
    "androidTestImplementation"("com.android.support.test:runner:1.0.1")
    "androidTestImplementation"("com.android.support.test.espresso:espresso-core:3.0.1")
}

configure<KtlintExtension> {
    version = "1.5.0"
    debug = true
    verbose = true
    android = false
    reporter = ReporterType.CHECKSTYLE
    ignoreFailures = true
}