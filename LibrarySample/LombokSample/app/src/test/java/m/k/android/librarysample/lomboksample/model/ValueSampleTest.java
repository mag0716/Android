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
public class ValueSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_ValueSample_コンストラクタで指定した値が正しくセットされていることを確認() {
        ValueSample sample = new ValueSample(1, "title");

        Assert.assertEquals("mNumberにコンストラクタで指定した値がセットされていること", 1, sample.getNumber());
        Assert.assertEquals("mTitleにコンストラクタで指定した値がセットされていること", "title", sample.getTitle());
    }

    @Test
    public void test_ValueSample_コンストラクタで指定したnullが正しくセットされていることを確認() {
        ValueSample sample = new ValueSample(1, null);

        Assert.assertEquals("mNumberにコンストラクタで指定した値がセットされていること", 1, sample.getNumber());
        Assert.assertEquals("mTitleにコンストラクタで指定した値がセットされていること", null, sample.getTitle());
    }

    @Test
    public void test_getter_コンストラクタで指定した値が正しく取得できることを確認() {
        ValueSample sample = new ValueSample(2, "title");

        Assert.assertEquals("mNumberにコンストラクタで指定した値が返却されること", 2, sample.getNumber());
        Assert.assertEquals("mTitleにコンストラクタで指定した値が返却されること", "title", sample.getTitle());
    }

    @Test
    public void test_equals_同じ値を指定したオブジェクトを比較しtrueが返却されることを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(2, "title");

        Assert.assertTrue("同じ値を指定したオブジェクトが一致すること", sample1.equals(sample2));
    }

    @Test
    public void test_equals_別の値を指定したオブジェクトを比較しfalseが返却されることを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(3, "Title");

        Assert.assertFalse("異なる値を指定したオブジェクトが一致しないこと", sample1.equals(sample2));
    }

    @Test
    public void test_hashCode_hashCodeが返却されることを確認() {
        ValueSample sample = new ValueSample(2, "title");

        Assert.assertNotSame("hashCodeが返却されること", 0, sample.hashCode());
    }

    @Test
    public void test_hashCode_同じ値を指定したオブジェクトのhashCodeと一致することを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(2, "title");

        Assert.assertEquals("同じ値を指定したオブジェクトのhashCodeが一致すること", sample1.hashCode(), sample2.hashCode());
    }

    @Test
    public void test_hashCode_別の値を指定したオブジェクトのhashCodeと異なることを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(3, "Title");

        Assert.assertNotSame("異なる値を指定したオブジェクトのhashCodeが一致しないこと", sample1.hashCode(), sample2.hashCode());
    }

    @Test
    public void test_toString_オブジェクトの情報が文字列で返却されることを確認() {
        ValueSample sample = new ValueSample(2, "title");

        Assert.assertNotSame("toStringの返却値がnullでないこと", null, sample.toString());
        Assert.assertNotSame("toStringの返却値が空文字でないこと", "", sample.toString());
    }

    @Test
    public void test_toString_同じ値を指定したオブジェクトのtoStringと一致することを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(2, "title");

        Assert.assertEquals("同じ値を指定したオブジェクトのtoStringが一致すること", sample1.toString(), sample2.toString());
    }

    @Test
    public void test_toString_別の値を指定したオブジェクトのtoStringと異なることを確認() {
        ValueSample sample1 = new ValueSample(2, "title");
        ValueSample sample2 = new ValueSample(3, "Title");

        Assert.assertNotSame("同じ値を指定したオブジェクトのtoStringが一致すること", sample1.toString(), sample2.toString());
    }
}
