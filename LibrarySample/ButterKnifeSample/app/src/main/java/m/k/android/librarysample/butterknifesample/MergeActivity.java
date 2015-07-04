package m.k.android.librarysample.butterknifesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Bind;


public class MergeActivity extends ActionBarActivity {

    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        ButterKnife.bind(this);

        mImage.setImageResource(R.mipmap.ic_launcher);
        mText.setText(mText.getText() + "inject success");
    }
}
