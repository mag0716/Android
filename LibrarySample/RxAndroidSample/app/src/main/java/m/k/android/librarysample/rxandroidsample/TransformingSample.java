package m.k.android.librarysample.rxandroidsample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by kishimotomasashi on 2015/05/23.
 * https://github.com/ReactiveX/RxJava/wiki/Transforming-Observables
 */
public class TransformingSample {

    public void map(Integer[] values, Func1<Integer, Integer> func, Subscriber<Integer> subscriber) {
        Observable.from(values)
                .map(func)
                .subscribe(subscriber);
    }

    public void flatMap(Integer[] values, Func1<Integer, Observable<Object>> func, Subscriber<Object> subscriber) {
        Observable.from(values)
                .flatMap(func)
                .subscribe(subscriber);
    }
}
