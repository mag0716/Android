package m.k.android.librarysample.lomboksample.model;

import org.junit.After;
import org.junit.Assert;
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
public class NonNullSampleTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test_setText_null以外の値を代入し正しい結果となることを確認() {
        NonNullSample sample = new NonNullSample();
        sample.setText("test");

        Assert.assertEquals("代入した値が返却されること", "test", sample.getText());
    }

    @Test
    public void test_setText_nullを代入しNullPointerExceptionが発生することを確認() {
        NonNullSample sample = new NonNullSample();

        try {
            sample.setText(null);
            Assert.assertTrue("setTextにnullを渡しNullPointerExceptionが発生すること", false);
        } catch(NullPointerException e) {
            Assert.assertTrue("setTextにnullを渡しNullPointerExceptionが発生すること", true);
        } catch(Exception e) {
            Assert.assertTrue("setTextにnullを渡しNullPointerException以外のExceptionが発生しないこと", false);
        }
    }

    @Test
    public void test_equals_null以外の値を代入し正しい結果となることを確認() {
        NonNullSample sample = new NonNullSample();
        Assert.assertTrue(sample.equals("NonNullSample"));
        Assert.assertFalse(sample.equals("NonNullSample!!"));
        Assert.assertFalse(sample.equals(""));
    }

    @Test
    public void test_equals_nullを代入しNullPointerExceptionが発生することを確認() {
        NonNullSample sample = new NonNullSample();
        try {
            sample.equals(null);
            Assert.assertTrue("equalsにnullを渡しNullPointerExceptionが発生すること", false);
        } catch(NullPointerException e) {
            Assert.assertTrue("equalsにnullを渡しNullPointerExceptionが発生すること", true);
        } catch(Exception e) {
            Assert.assertTrue("equalsにnullを渡しNullPointerException以外のExceptionが発生しないこと", false);
        }
    }

    @Test
    public void test_getData_nullを返却するメソッドにNonNullを指定しても何も起きないことを確認() {
        NonNullSample sample = new NonNullSample();

        try {
            Assert.assertNull("getDataからnullが返却されること", sample.getData());
        } catch(Exception e) {
            Assert.assertTrue("getDataの呼び出しでExceptionが発生しないこと", false);
        }
    }
}
