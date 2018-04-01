package com.github.mag0716.testsample;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

import java.net.MalformedURLException;
import java.net.URL;

public class UseMockAssetsTestRunner extends RobolectricTestRunner {
    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link Config} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public UseMockAssetsTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        final String appRoot = "./src/main/";
        final String manifestPath = appRoot + "AndroidManifest.xml";
        final String resDir = appRoot + "res";
        final String assetsDir = "./src/mock/assets";
        try {
            return new AndroidManifest(
                    Fs.fromURL(new URL(manifestPath)),
                    Fs.fromURL(new URL(resDir)),
                    Fs.fromURL(new URL(assetsDir)));
        } catch (MalformedURLException e) {
            return super.getAppManifest(config);
        }
    }
}
