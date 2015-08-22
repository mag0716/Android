package m.k.android.sample.sharedatasample2;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
        String data = "NO DATA.";
        try {
            data = pref("via_broadcast_preference").getString("sample", data);
        } catch (PackageManager.NameNotFoundException e) {}

        mEdit.setText(data);
    }

    @OnClick(R.id.save_btn)
    public void save() {
        // ShareDataSample に Broadcast を通知し、プリファレンスの値を更新する
        final Intent intent = new Intent();
        intent.setAction("m.k.android.sample.sharedatasample.DATA_CHANGE");
        intent.putExtra("value", mEdit.getText().toString());
        sendBroadcast(intent);
    }

    @OnClick(R.id.load_contentprovider_btn)
    public void loadByContentProvider() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://m.k.android.sample.sharedatasample.preferenceprovider/preference"),
                    null, null, null, null);

        String[] columns = cursor.getColumnNames();
        int keyIndex = cursor.getColumnIndex("key");
        int valueIndex = cursor.getColumnIndex("value");

        while(cursor.moveToNext()) {
            if("sample".equals(cursor.getString(keyIndex))) {
                mEdit.setText(cursor.getString(valueIndex));
            }
        }
    }

    @OnClick(R.id.save_contentprovider_btn)
    public void saveByContentnProvider() {

    }

    private SharedPreferences pref(String name) throws PackageManager.NameNotFoundException {
        // ShareDataSample のプリファレンスを取得する
        Context context = createPackageContext("m.k.android.sample.sharedatasample", Context.CONTEXT_RESTRICTED);
        return context.getSharedPreferences(name, Context.MODE_WORLD_READABLE|Context.MODE_MULTI_PROCESS);
    }
}
