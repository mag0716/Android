package m.k.android.librarysample.okhttpsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView mResultText;
    private ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultText = (TextView)findViewById(R.id.result_text);
        mImage = (ImageView)findViewById(R.id.image);
    }

    public void onClick(View view) {

    }
}
