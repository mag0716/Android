package m.k.android.librarysample.butterknifesample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "ButterKnifeSample";

    @Bind(R.id.fragment_sample_btn)
    Button mFragmentSampleBtn;
    @Bind(R.id.listview_sample_btn)
    Button mListViewSampleBtn;
    @Bind(R.id.include_sample_btn)
    Button mIncludeSampleBtn;
    @Bind(R.id.merge_sample_btn)
    Button mMergeSampleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fragment_sample_btn)
    public void startFragmentSample(View view) {
        Log.d(TAG, "startFragmentSample");
        startActivity(new Intent(this, FragmentSampleActivity.class));
    }

    @OnClick(R.id.listview_sample_btn)
    public void startListViewSample(View view) {
        Log.d(TAG, "startListViewSample");
        startActivity(new Intent(this, ListViewSampleActivity.class));
    }

    @OnClick(R.id.include_sample_btn)
    public void startIncludeSample(View view) {
        Log.d(TAG, "startIncludeSample");
        startActivity(new Intent(this, IncludeActivity.class));
    }

    @OnClick(R.id.merge_sample_btn)
    public void startMergeSample(View view) {
        Log.d(TAG, "startMergeSample");
        startActivity(new Intent(this, MergeActivity.class));
    }
}
