package m.k.android.librarysample.rxandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public class SubjectActivity extends AppCompatActivity {

    private static final String TAG = SubjectActivity.class.getSimpleName();

    @InjectView(R.id.subject_spinner)
    Spinner mSubjectSpinner;

    private Subject<Integer, Integer> mSubject;
    private Subscription mSubscription1, mSubscription2;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        ButterKnife.inject(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.add("AsyncSubject");
        adapter.add("BehaviorSubject");
        adapter.add("PublishSubject");
        adapter.add("ReplaySubject");
        mSubjectSpinner.setAdapter(adapter);
        mSubjectSpinner.setSelection(0);
    }

    @OnClick(R.id.create_btn)
    void create() {
        Log.d(TAG, "create Subject.");
        if(mSubscription1 != null && !mSubscription1.isUnsubscribed()) {
            mSubscription1.unsubscribe();
        }
        if(mSubscription2 != null && !mSubscription2.isUnsubscribed()) {
            mSubscription2.unsubscribe();
        }

        switch(mSubjectSpinner.getSelectedItemPosition()) {
            case 0:
                mSubject = AsyncSubject.create();
                break;
            case 1:
                mSubject = BehaviorSubject.create();
                break;
            case 2:
                mSubject = PublishSubject.create();
                break;
            case 3:
                mSubject = ReplaySubject.create();
                break;
        }
    }

    @OnClick(R.id.next_btn)
    void next() {
        if(mSubject != null) {
            Log.d(TAG, "onNext");
            mSubject.onNext(mCount++);
        } else {
            Log.w(TAG, "not create Subject");
        }
    }

    @OnClick(R.id.complete_btn)
    void complete() {
        if(mSubject != null) {
            Log.d(TAG, "onCompleted");
            mSubject.onCompleted();
        } else {
            Log.w(TAG, "not create Subject");
        }
    }

    @OnClick(R.id.error_btn)
    void error() {
        if(mSubject != null) {
            Log.d(TAG, "onError");
            mSubject.onError(new Exception("error"));
        } else {
            Log.w(TAG, "not create Subject");
        }
    }

    @OnClick(R.id.subscribe1_btn)
    void subscribe1() {
        if(mSubject != null) {
            Log.d(TAG, "subscribe1");
            mSubscription1 = mSubject.subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "Subscriber 1 : onCompleted.");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "Subscriber 1 : onError.");
                }

                @Override
                public void onNext(Integer integer) {
                    Log.d(TAG, "Subscriber 1 : onNext : " + integer);
                }
            });
        } else {
            Log.w(TAG, "not create Subject");
        }
    }

    @OnClick(R.id.subscribe2_btn)
    void subscribe2() {
        if(mSubject != null) {
            Log.d(TAG, "subscribe2");
            mSubscription2 = mSubject.subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "Subscriber 2 : onCompleted.");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "Subscriber 2 : onError.");
                }

                @Override
                public void onNext(Integer integer) {
                    Log.d(TAG, "Subscriber 2 : onNext : " + integer);
                }
            });
        } else {
            Log.w(TAG, "not create Subject");
        }
    }

    @OnClick(R.id.unsubscribe1_btn)
    void unsubscribe1() {
        if(mSubscription1 != null && !mSubscription1.isUnsubscribed()) {
            Log.d(TAG, "unsubscribe1");
            mSubscription1.unsubscribe();
        } else {
            Log.w(TAG, "not subscribed 1.");
        }
    }

    @OnClick(R.id.unsubscribe2_btn)
    void unsubscribe2() {
        if(mSubscription2 != null && !mSubscription2.isUnsubscribed()) {
            Log.d(TAG, "unsubscribe2");
            mSubscription2.unsubscribe();
        } else {
            Log.w(TAG, "not subscribed 2.");
        }
    }
}
