package m.k.android.librarysample.butterknifesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MergeActivity extends ActionBarActivity {

    @InjectView(R.id.image)
    ImageView mImage;
    @InjectView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        ButterKnife.inject(this);

        mImage.setImageResource(R.mipmap.ic_launcher);
        mText.setText(mText.getText() + "inject success");
    }
}
