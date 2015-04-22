package m.k.android.librarysample.rxandroidsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "RxAndroidSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn1)
    void useSimple() {
        Observable.from(new Integer[]{1, 2, 3, 4, 5})
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    Log.d(TAG, "sample : " + integer);
                }
            });
    }

    @OnClick(R.id.btn2)
    void useMap() {
        Observable.from(new Integer[]{1, 2, 3, 4, 5})
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * integer;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "sample : " + integer);
                    }
                });
    }

    @OnClick(R.id.btn3)
    void useFlatMap() {
        Observable.from(new Integer[]{1, 2, 3, 4, 5})
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        Integer[] values = new Integer[integer];
                        for (int i = 0; i < integer; i++) {
                            values[i] = integer;
                        }
                        return Observable.from(values);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "sample : " + integer);
                    }
                });
    }

    @OnClick(R.id.btn4)
    void useFilter() {
        Observable.from(new Integer[]{1, 2, 3, 4, 5})
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "sample : " + integer);
                    }
                });
    }
}
