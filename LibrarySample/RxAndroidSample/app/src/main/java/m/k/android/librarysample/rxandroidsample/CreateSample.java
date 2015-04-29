package m.k.android.librarysample.rxandroidsample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by kishimotomasashi on 2015/04/29.
 * https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
 */
public class CreateSample {

    public void just(Integer value, Subscriber<Integer> subscriber) {
        Observable.just(value)
                .subscribe(subscriber);
    }

    public void from(Integer[] values, Subscriber<Integer> subscriber) {
        Observable.from(values)
                .subscribe(subscriber);
    }

    public void repeat(Integer value, Func1<Integer, Boolean> repeatCondition, Subscriber<Integer> subscriber) {
        // repeat() だけだと、無限に onNext が実行される
        Observable.just(value)
                .repeat()
                .takeWhile(repeatCondition)
                .subscribe(subscriber);
    }

    public void repeat(Integer value, int repeatCount, Subscriber<Integer> subscriber) {
        Observable.just(value)
                .repeat(repeatCount)
                .subscribe(subscriber);
    }

    public void repeatWhen(Integer value, Func1<Observable, Observable<Integer>> notificationHandler, int takeCount, Subscriber<Integer> subscriber) {
        Observable.just(value)
                .repeatWhen(notificationHandler)
                .take(takeCount)
                .subscribe(subscriber);
    }
}
