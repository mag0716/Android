package m.k.android.librarysample.retrofitsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView)findViewById(R.id.result_text);
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn1:
                executeSingleApi();
                break;
            case R.id.btn2:
                executeMultiApi();
                break;
            case R.id.btn3:
                executeFailApi();
                break;
        }
    }

    private void executeSingleApi() {

    }

    private void executeMultiApi() {

    }

    private void executeFailApi() {

    }
}
