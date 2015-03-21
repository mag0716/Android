package m.k.android.librarysample.lomboksample.model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import lombok.EqualsAndHashCode;

/**
 * Created by kishimotomasashi on 2015/03/21.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class EqualsAndHashCodeSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_DefaultConfigData_hashCode_親クラスのデータが異なっていてもhashCodeが一致することを確認() {
        EqualsAndHashCodeSample.DefaultConfigData data1 = new EqualsAndHashCodeSample.DefaultConfigData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.DefaultConfigData data2 = new EqualsAndHashCodeSample.DefaultConfigData();
        data2.setNumber(2);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertEquals("親クラスのデータが異なっていてもhashCodeが一致すること", data1.hashCode(), data2.hashCode());
    }

    @Test
    public void test_DefaultConfigData_hashCode_異なる値をセットしhashCodeが一致しないことを確認() {
        EqualsAndHashCodeSample.DefaultConfigData data1 = new EqualsAndHashCodeSample.DefaultConfigData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.DefaultConfigData data2 = new EqualsAndHashCodeSample.DefaultConfigData();
        data2.setNumber(2);
        data2.setName("Name");
        data2.setPassword("Password");

        Assert.assertNotSame("異なる値のオブジェクトのhashCodeが一致しないこと", data1.hashCode(), data2.hashCode());
    }

    @Test
    public void test_DefaultConfigData_equals_親クラスのデータが異なっていてもequalsがtrueを返却すること確認() {
        EqualsAndHashCodeSample.DefaultConfigData data1 = new EqualsAndHashCodeSample.DefaultConfigData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.DefaultConfigData data2 = new EqualsAndHashCodeSample.DefaultConfigData();
        data2.setNumber(2);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertTrue("親クラスのデータが異なっていてもequalsがtrueを返却すること", data1.equals(data2));
    }

    @Test
    public void test_DefaultConfigData_equals_異なる値をセットしequalsがfalseを返却することを確認() {
        EqualsAndHashCodeSample.DefaultConfigData data1 = new EqualsAndHashCodeSample.DefaultConfigData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.DefaultConfigData data2 = new EqualsAndHashCodeSample.DefaultConfigData();
        data2.setNumber(2);
        data2.setName("Name");
        data2.setPassword("Password");

        Assert.assertFalse("異なる値のオブジェクトを比較しequalsがfalseを返却すること", data1.equals(data2));
    }

    @Test
    public void test_ExcludePasswordData_パスワードが異なっていてもhashCodeが一致することを確認() {
        EqualsAndHashCodeSample.ExcludePasswordData data1 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.ExcludePasswordData data2 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data2.setNumber(1);
        data2.setName("name");
        data2.setPassword("PASSWORD");

        Assert.assertEquals("パスワードが異なっていてもhashCodeが一致すること", data1.hashCode(), data2.hashCode());

    }

    @Test
    public void test_ExcludePasswordData_異なる値をセットしhashCodeが一致しないことを確認() {
        EqualsAndHashCodeSample.ExcludePasswordData data1 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.ExcludePasswordData data2 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data2.setNumber(2);
        data2.setName("Name");
        data2.setPassword("PASSWORD");

        Assert.assertNotSame("異なる値のオブジェクトのhashCodeが一致しないこと", data1.hashCode(), data2.hashCode());
    }

    @Test
    public void test_ExcludePasswordData_equals_パスワードが異なっていてもequalsがtrueを返却すること確認() {
        EqualsAndHashCodeSample.ExcludePasswordData data1 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.ExcludePasswordData data2 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data2.setNumber(1);
        data2.setName("name");
        data2.setPassword("PASSWORD");

        Assert.assertTrue("パスワードが異なっていてもequalsがtrueを返却すること", data1.equals(data2));
    }

    @Test
    public void test_ExcludePasswordData_equals_異なる値をセットしequalsがfalseを返却することを確認() {
        EqualsAndHashCodeSample.ExcludePasswordData data1 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.ExcludePasswordData data2 = new EqualsAndHashCodeSample.ExcludePasswordData();
        data2.setNumber(2);
        data2.setName("Name");
        data2.setPassword("PASSWORD");

        Assert.assertFalse("異なる値のオブジェクトを比較しequalsがfalseを返却すること", data1.equals(data2));
    }

    @Test
    public void test_CallSuperData_hashCode_同じデータのオブジェクトのhashCodeが一致することを確認() {
        EqualsAndHashCodeSample.CallSuperData data1 = new EqualsAndHashCodeSample.CallSuperData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.CallSuperData data2 = new EqualsAndHashCodeSample.CallSuperData();
        data2.setNumber(1);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertEquals("同じデータのオブジェクトのhashCodeが一致すること", data1.hashCode(), data2.hashCode());
    }

    @Test
    public void test_CallSuperData_hashCode_親クラスのデータのみが異なるオブジェクトのhashCodeが一致しないことを確認() {
        EqualsAndHashCodeSample.CallSuperData data1 = new EqualsAndHashCodeSample.CallSuperData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.CallSuperData data2 = new EqualsAndHashCodeSample.CallSuperData();
        data2.setNumber(2);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertNotSame("親クラスのデータのみが異なるオブジェクトのhashCodeが一致しないこと", data1.hashCode(), data2.hashCode());
    }

    @Test
    public void test_CallSuperData_equals_同じデータのオブジェクトが一致することを確認() {
        EqualsAndHashCodeSample.CallSuperData data1 = new EqualsAndHashCodeSample.CallSuperData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.CallSuperData data2 = new EqualsAndHashCodeSample.CallSuperData();
        data2.setNumber(1);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertTrue("同じデータのオブジェクトを比較しequalsがtrueを返却すること", data1.equals(data2));
    }

    @Test
    public void test_CallSuperData_equals_親クラスのデータのみが異なるオブジェクトが一致しないことを確認() {
        EqualsAndHashCodeSample.CallSuperData data1 = new EqualsAndHashCodeSample.CallSuperData();
        data1.setNumber(1);
        data1.setName("name");
        data1.setPassword("password");

        EqualsAndHashCodeSample.CallSuperData data2 = new EqualsAndHashCodeSample.CallSuperData();
        data2.setNumber(2);
        data2.setName("name");
        data2.setPassword("password");

        Assert.assertFalse("親クラスのデータのみが異なるオブジェクトを比較しequalsがtrueを返却すること", data1.equals(data2));
    }
}
