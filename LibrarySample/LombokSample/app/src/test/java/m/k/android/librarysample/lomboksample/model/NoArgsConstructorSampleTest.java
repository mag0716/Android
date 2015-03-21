package m.k.android.librarysample.lomboksample.model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class NoArgsConstructorSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_NoArgsConstructorSample_デフォルトコンストラクタで生成したオブジェクトにデフォルト値がセットされていることを確認() {
        NoArgsConstructorSample sample = new NoArgsConstructorSample();

        Assert.assertEquals("デフォルトコンストラクタで生成したオブジェクトにデフォルト値がセットされていること", 0, sample.getNumber());
    }
}
