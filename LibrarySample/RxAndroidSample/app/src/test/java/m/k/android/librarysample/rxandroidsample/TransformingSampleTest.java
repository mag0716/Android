package m.k.android.librarysample.rxandroidsample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.functions.Func1;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by kishimotomasashi on 2015/05/24.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class TransformingSampleTest {

    private TransformingSample mSample;

    @Before
    public void setUp() throws Exception {
        mSample = new TransformingSample();
    }

    @After
    public void tearDown() throws Exception {
        mSample = null;
    }

    @Test
    public void testMap_指定したfunctionが適用されることを確認する() {
        final List<String> result = new ArrayList<>();
        mSample.map(new Integer[]{1, 2, 3},
                new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                },
                new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                result.add("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                result.add("onError");
            }

            @Override
            public void onNext(Integer integer) {
                result.add("onNext : " + integer);
            }
        });

        Assert.assertThat(result.size(), is(4));
        Assert.assertThat(result.get(0), is("onNext : 2"));
        Assert.assertThat(result.get(1), is("onNext : 4"));
        Assert.assertThat(result.get(2), is("onNext : 6"));
        Assert.assertThat(result.get(3), is("onCompleted"));
    }

    @Test
    public void testMap_functionにnullを指定() {
        final List<String> result = new ArrayList<>();
        mSample.map(new Integer[]{1, 2, 3},
                null,
                new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        result.add("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.add("onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        result.add("onNext : " + integer);
                    }
                });

        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is("onError"));
    }
}
