package com.github.mag0716.testsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(UseMockAssetsTestRunner.class)
public class UseMockAssetsInTest {
    @Test
    public void テストデータとして特定flavorのassetsにあるリソースを取得できること() {
        try {
            final InputStream is = RuntimeEnvironment.application.getAssets().open("test.txt");
            assertThat(Sample.inputStreamToString(is), is("test"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
