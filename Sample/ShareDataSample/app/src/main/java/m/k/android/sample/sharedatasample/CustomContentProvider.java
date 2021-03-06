package m.k.android.sample.sharedatasample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import java.util.Map;

public class CustomContentProvider extends ContentProvider {

    private SharedPreferences mPref;

    public CustomContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mPref = getContext().getSharedPreferences(MainActivity.BROADCAST_TYPE, Context.MODE_WORLD_READABLE);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        Log.d("xxx", "CustomContentProvider#getType : uri = " + uri.toString());
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d("xxx", "CustomContentProvider#query : uri = " + uri.toString());
        MatrixCursor cursor = new MatrixCursor(new String[] {
                "key", "value"
        });

        // プリファレンスに保存されている全データを返却する
        Map all = mPref.getAll();
        Object value;
        for(Object key : all.keySet()) {
            value = all.get(key);
            Log.d("xxx", "add " + key + "(" + value + ")");
            cursor.addRow(new Object[]{
                    key, value});
        }

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d("xxx", "CustomContentProvider#update : uri = " + uri.toString());

        int count = 0;

        if(values != null) {
            SharedPreferences.Editor editor = mPref.edit();
            for(String key : values.keySet()) {
                // TODO: ひとまず String 型のみ対応
                editor.putString(key, values.getAsString(key));
            }
            editor.commit();
        }

        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
