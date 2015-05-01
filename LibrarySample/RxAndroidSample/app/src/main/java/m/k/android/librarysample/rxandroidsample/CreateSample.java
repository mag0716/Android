package m.k.android.librarysample.rxandroidsample;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
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

    public void create(Observable.OnSubscribe<Integer> observer, Subscriber<Integer> subscriber) {
        Observable.create(observer)
                .subscribe(subscriber);
    }

    public void defer(Func0<Observable<Integer>> observableFactory, Subscriber<Integer> subscriber) {
        Observable.defer(observableFactory)
                .subscribe(subscriber);

    }

    public void range(int start, int count, Subscriber<Integer> subscriber) {
        Observable.range(start, count)
                .subscribe(subscriber);
    }

    public void interval(long interval, TimeUnit timeUnit, Subscriber<Long> subscriber) {
        Observable.interval(interval, timeUnit)
                .subscribe(subscriber);
    }

    public void timer(long delay, TimeUnit timeUnit, Subscriber<Long> subscriber) {
        Observable.timer(delay, timeUnit)
                .subscribe(subscriber);
    }

    public void empty(Subscriber<Object> subscriber) {
        Observable.empty()
                .subscribe(subscriber);
    }

    public void error(Throwable e, Subscriber subscriber) {
        Observable.error(e)
                .subscribe(subscriber);
    }

    public void never(Subscriber<Object> subscriber) {
        Observable.never()
                .subscribe(subscriber);
    }
}
