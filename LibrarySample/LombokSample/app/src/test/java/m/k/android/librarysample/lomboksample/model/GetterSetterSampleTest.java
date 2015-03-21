package m.k.android.librarysample.lomboksample.model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by kishimotomasashi on 2015/03/22.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class GetterSetterSampleTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void test_DefaultConfigData_getter_デフォルトコンストラクタで生成した場合はデフォルト値を取得できることを確認() {
        GetterSetterSample.DefaultConfigData data = new GetterSetterSample.DefaultConfigData();

        Assert.assertEquals("デフォルト値が取得できること", 0, data.getNumber());
        Assert.assertEquals("デフォルト値が取得できること", null, data.getTitle());
        Assert.assertEquals("デフォルト値が取得できること", false, data.isEnabled());
    }

    @Test
    public void test_DefaultConfigData_getter_コンストラクタで指定した値を取得できることを確認() {
        GetterSetterSample.DefaultConfigData data = new GetterSetterSample.DefaultConfigData(1, "title", true);

        Assert.assertEquals("コンストラクタで指定した値が取得できること", 1, data.getNumber());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", "title", data.getTitle());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", true, data.isEnabled());
    }

    @Test
    public void test_DefaultConfigData_setter_デフォルトコンストラクタで生成したオブジェクトに対して値をセットできることを確認() {
        GetterSetterSample.DefaultConfigData data = new GetterSetterSample.DefaultConfigData();

        Assert.assertEquals("デフォルト値が取得できること", 0, data.getNumber());
        Assert.assertEquals("デフォルト値が取得できること", null, data.getTitle());
        Assert.assertEquals("デフォルト値が取得できること", false, data.isEnabled());

        data.setNumber(1);
        data.setTitle("title");
        data.setEnabled(true);

        Assert.assertEquals("setterでセットした値が取得できること", 1, data.getNumber());
        Assert.assertEquals("setterでセットした値が取得できること", "title", data.getTitle());
        Assert.assertEquals("setterでセットした値が取得できること", true, data.isEnabled());
    }

    @Test
    public void test_DefaultConfigData_setter_引数ありのコンストラクタで生成したオブジェクトに対して値をセットできることを確認() {
        GetterSetterSample.DefaultConfigData data = new GetterSetterSample.DefaultConfigData(1, "title", true);

        Assert.assertEquals("コンストラクタで指定した値が取得できること", 1, data.getNumber());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", "title", data.getTitle());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", true, data.isEnabled());

        data.setNumber(2);
        data.setTitle("TITLE");
        data.setEnabled(false);

        Assert.assertEquals("setterでセットした値が取得できること", 2, data.getNumber());
        Assert.assertEquals("setterでセットした値が取得できること", "TITLE", data.getTitle());
        Assert.assertEquals("setterでセットした値が取得できること", false, data.isEnabled());
    }


    @Test
    public void test_ChainAndFluentData_getter_デフォルトコンストラクタで生成した場合はデフォルト値を取得できることを確認() {
        GetterSetterSample.ChainAndFluentData data = new GetterSetterSample.ChainAndFluentData();

        Assert.assertEquals("デフォルト値が取得できること", 0, data.number());
        Assert.assertEquals("デフォルト値が取得できること", null, data.title());
        Assert.assertEquals("デフォルト値が取得できること", false, data.enabled());
    }

    @Test
    public void test_ChainAndFluentData_getter_コンストラクタで指定した値を取得できることを確認() {
        GetterSetterSample.ChainAndFluentData data = new GetterSetterSample.ChainAndFluentData(1, "title", true);

        Assert.assertEquals("コンストラクタで指定した値が取得できること", 1, data.number());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", "title", data.title());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", true, data.enabled());
    }

    @Test
    public void test_ChainAndFluentData_setter_デフォルトコンストラクタで生成したオブジェクトに対して値をセットできることを確認() {
        GetterSetterSample.ChainAndFluentData data = new GetterSetterSample.ChainAndFluentData();

        Assert.assertEquals("デフォルト値が取得できること", 0, data.number());
        Assert.assertEquals("デフォルト値が取得できること", null, data.title());
        Assert.assertEquals("デフォルト値が取得できること", false, data.enabled());

        data.number(1).title("title").enabled(true);

        Assert.assertEquals("setterでセットした値が取得できること", 1, data.number());
        Assert.assertEquals("setterでセットした値が取得できること", "title", data.title());
        Assert.assertEquals("setterでセットした値が取得できること", true, data.enabled());
    }

    @Test
    public void test_ChainAndFluentData_setter_引数ありのコンストラクタで生成したオブジェクトに対して値をセットできることを確認() {
        GetterSetterSample.ChainAndFluentData data = new GetterSetterSample.ChainAndFluentData(1, "title", true);

        Assert.assertEquals("コンストラクタで指定した値が取得できること", 1, data.number());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", "title", data.title());
        Assert.assertEquals("コンストラクタで指定した値が取得できること", true, data.enabled());

        data.number(2).title("TITLE").enabled(false);

        Assert.assertEquals("setterでセットした値が取得できること", 2, data.number());
        Assert.assertEquals("setterでセットした値が取得できること", "TITLE", data.title());
        Assert.assertEquals("setterでセットした値が取得できること", false, data.enabled());
    }
}
