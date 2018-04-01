package com.github.mag0716.testsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class SampleTest {

    @Test
    public void アプリで定義したリソースを取得できること() {
        assertThat(Sample.getApplicationName(RuntimeEnvironment.application), is("TestSample"));
    }

    @Test
    public void assetsにあるリソースを取得できること() {
        try {
            assertThat(Sample.getAssetsFile(RuntimeEnvironment.application), is("sample"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
