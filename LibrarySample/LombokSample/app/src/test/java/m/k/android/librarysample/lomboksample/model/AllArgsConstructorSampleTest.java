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
public class AllArgsConstructorSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_AllArgsConstructorSample_全ての変数を引数に持ったコンストラクタが生成されることを確認() {
        AllArgsConstructorSample sample = new AllArgsConstructorSample(1, "name", "password");

        Assert.assertEquals("全ての変数を引数に持ったコンストラクタが生成されること",
                1, sample.getNumber());
        Assert.assertEquals("全ての変数を引数に持ったコンストラクタが生成されること",
                "name", sample.getName());
        Assert.assertSame("全ての変数を引数に持ったコンストラクタが生成されること",
                "password", sample.getPassword());
    }

    @Test
    public void test_AllArgsConstructorSample_NonNullを指定した変数にnullを指定するとNullPointerExceptionがthrowされることを確認() {
        try {
            new AllArgsConstructorSample(1, "name", null);
            Assert.assertTrue("NullPointerExceptionがthrowされること", false);
        } catch(NullPointerException e) {
            Assert.assertTrue("NullPointerExceptionがthrowされること", true);
        } catch(Exception e) {
            Assert.assertTrue("NullPointerExceptionがthrowされること", false);
        }
    }
}
