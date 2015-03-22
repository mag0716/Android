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
public class RequiredArgsConstructorSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_RequiredArgsConstructorSample_finalやNonNullを指定した変数のコンストラクタが生成されることを確認() {
        RequiredArgsConstructorSample sample = new RequiredArgsConstructorSample("name", "password");

        Assert.assertEquals("finalや@NonNullが指定されていない変数はコンストラクタで値を指定しないこと",
                0, sample.getNumber());
        Assert.assertEquals("finalを指定した変数はコンストラクタで値を指定すること",
                "name", sample.getName());
        Assert.assertSame("@NonNullを指定した変数はコンストラクタで値を指定すること",
                "password", sample.getPassword());
    }

    @Test
    public void test_RequiredArgsConstructorSample_NonNullを指定した変数にnullを指定するとNullPointerExceptionがthrowされることを確認() {
        try {
            new RequiredArgsConstructorSample("name", null);
            Assert.assertTrue("NullPointerExceptionがthrowされること", false);
        } catch(NullPointerException e) {
            Assert.assertTrue("NullPointerExceptionがthrowされること", true);
        } catch(Exception e) {
            Assert.assertTrue("NullPointerExceptionがthrowされること", false);
        }
    }
}
