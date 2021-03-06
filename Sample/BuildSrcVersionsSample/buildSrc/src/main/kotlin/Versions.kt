import kotlin.String
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.6.1"

    const val com_android_tools_build_gradle: String = "3.6.0-alpha12"

    const val androidx_test_ext_junit: String = "1.1.1"

    const val org_jetbrains_kotlin: String = "1.3.50"

    const val constraintlayout: String = "1.1.3"

    const val espresso_core: String = "3.2.0"

    const val junit_junit: String = "4.12"

    const val lint_gradle: String = "26.6.0-alpha12"

    const val appcompat: String = "1.1.0"

    const val core_ktx: String = "1.1.0"

    const val aapt2: String = "3.6.0-alpha12-5836729"

    /**
     * Current version: "5.6.1"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "5.6.2"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
    inline get() =
            id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
