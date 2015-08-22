package m.k.android.sample.sharedatasample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_TYPE = "via_broadcast_preference";

    @Bind(R.id.edit)
    EditText mEdit;
    @Bind(R.id.load_btn)
    Button mLoadBtn;
    @Bind(R.id.save_btn)
    Button mSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.load_btn)
    public void load() {
        mEdit.setText(pref(this, BROADCAST_TYPE).getString("sample", "NO DATA."));
    }

    @OnClick(R.id.save_btn)
    public void save() {
        pref(this, BROADCAST_TYPE).edit().putString("sample", mEdit.getText().toString()).commit();
    }

    private SharedPreferences pref(Context context, String name) {
        return context.getSharedPreferences(name, MODE_WORLD_READABLE|MODE_MULTI_PROCESS);
    }
}
