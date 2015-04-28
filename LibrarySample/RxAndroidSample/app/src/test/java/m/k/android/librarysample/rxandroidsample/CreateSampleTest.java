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
 * Created by kishimotomasashi on 2015/04/29.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class CreateSampleTest {

    private CreateSample mSample;

    @Before
    public void setUp() throws Exception {
        mSample = new CreateSample();
    }

    @After
    public void tearDown() throws Exception {
        mSample = null;
    }

    @Test
    public void test_just() {
        final List<String> result = new ArrayList<>();
        mSample.just(1, new Subscriber<Integer>() {
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

        Assert.assertThat(result.size(), is(2));
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onCompleted"));
    }

    @Test
    public void test_from() {
        final List<String> result = new ArrayList<>();
        mSample.from(new Integer[]{1, 2, 3}, new Subscriber<Integer>() {
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
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onNext : 2"));
        Assert.assertThat(result.get(2), is("onNext : 3"));
        Assert.assertThat(result.get(3), is("onCompleted"));
    }

    @Test
    public void test_repeat_onNextが1回呼ばれるまでrepeat() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(1, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return result.size() < 1;
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

        Assert.assertThat(result.size(), is(2));
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onCompleted"));
    }

    @Test
    public void test_repeat_onNextが3回呼ばれるまでrepeat() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(1, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return result.size() < 3;
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
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onNext : 1"));
        Assert.assertThat(result.get(2), is("onNext : 1"));
        Assert.assertThat(result.get(3), is("onCompleted"));
    }

    @Test
    public void test_repeat_onNextが1回も呼ばれない() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(1, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return false;
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

        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is("onCompleted"));
    }

    @Test
    public void test_repeat_repeatの引数に1を指定() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(1, 1, new Subscriber<Integer>() {
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

        Assert.assertThat(result.size(), is(2));
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onCompleted"));
    }

    @Test
    public void test_repeat_repeatの引数に3を指定() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(3, 1, new Subscriber<Integer>() {
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
        Assert.assertThat(result.get(0), is("onNext : 1"));
        Assert.assertThat(result.get(1), is("onNext : 1"));
        Assert.assertThat(result.get(2), is("onNext : 1"));
        Assert.assertThat(result.get(3), is("onCompleted"));
    }

    @Test
    public void test_repeat_repeatの引数に0を指定() {
        final List<String> result = new ArrayList<>();
        mSample.repeat(0, 1, new Subscriber<Integer>() {
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
        Assert.assertThat(result.get(0), is("onCompleted"));
    }
}
