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
public class ToStringSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_DefaultConfigData_toString_指定した値がtoStringで取得できることを確認() {
        ToStringSample.DefaultConfigData data = new ToStringSample.DefaultConfigData();
        data.setNumber(1);
        data.setName("name");
        data.setPassword("password");

        Assert.assertEquals("指定した値がtoStringで取得できること",
                "ToStringSample.DefaultConfigData(mName=name, mPassword=password)",
                data.toString());
    }

    @Test
    public void test_DefaultConfigData_toString_nullを指定しtoStringで取得できることを確認() {
        ToStringSample.DefaultConfigData data = new ToStringSample.DefaultConfigData();
        data.setNumber(1);
        data.setName(null);
        data.setPassword(null);

        Assert.assertEquals("指定した値がtoStringで取得できること",
                "ToStringSample.DefaultConfigData(mName=null, mPassword=null)",
                data.toString());
    }

    @Test
    public void test_ExcludePasswordData_toString_mPasswordがtoStringで取得できないことを確認() {
        ToStringSample.ExcludePasswordData data = new ToStringSample.ExcludePasswordData();
        data.setNumber(1);
        data.setName("name");
        data.setPassword("password");

        Assert.assertEquals("mPasswordがtoStringで取得できないこと",
                "ToStringSample.ExcludePasswordData(mName=name)",
                data.toString());
    }

    @Test
    public void test_CallSuperAndNotIncludeFiledNamesData_toString_親クラスの情報がtoStringで取得できることを確認() {
        ToStringSample.CallSuperAndNotIncludeFiledNamesData data = new ToStringSample.CallSuperAndNotIncludeFiledNamesData();
        data.setNumber(1);
        data.setName("name");
        data.setPassword("password");

        Assert.assertEquals("親クラスの情報がtoStringで取得できること",
                "ToStringSample.CallSuperAndNotIncludeFiledNamesData(super=ToStringSample.Data(mNumber=1), name, password)",
                data.toString());
    }

    @Test
    public void test_CallSuperAndNotIncludeFiledNamesData_toString_変数名がtoStringで取得できないことを確認() {

        ToStringSample.CallSuperAndNotIncludeFiledNamesData data = new ToStringSample.CallSuperAndNotIncludeFiledNamesData();
        data.setNumber(1);
        data.setName("name");
        data.setPassword("password");

        Assert.assertEquals("変数名がtoStringで取得できないこと",
                "ToStringSample.CallSuperAndNotIncludeFiledNamesData(super=ToStringSample.Data(mNumber=1), name, password)",
                data.toString());
    }
}
