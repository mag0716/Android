package m.k.android.librarysample.butterknifesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IncludeActivity extends ActionBarActivity {

    @Bind(R.id.text1)
    TextView mText1;
    @Bind(R.id.text2)
    TextView mText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        ButterKnife.bind(this);

        mText1.setText(mText1.getText() + "mText1 : inject success");
        mText2.setText(mText2.getText() + "mText2 : inject success");
    }
}
