import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.repositories

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra { "1.2.0" }
    repositories {
        google()
        jcenter()
        maven { url = uri("http://dl.bintray.com/kotlin/kotlin-eap-1.2") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:3.0.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.back files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
