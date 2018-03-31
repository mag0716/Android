package com.github.mag0716.testsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SampleTest {

    @Test
    public void アプリで定義したリソースを取得できること() {
        assertThat(Sample.getApplicationName(RuntimeEnvironment.application), is("TestSample"));
    }

    @Test
    public void assetsにあるリソースを取得できること() {
        assertThat(Sample.getAssetsFile(RuntimeEnvironment.application), is("sample"));
    }

}
