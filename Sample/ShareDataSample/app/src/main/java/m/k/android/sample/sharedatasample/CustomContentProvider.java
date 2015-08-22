package m.k.android.sample.sharedatasample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.Map;

public class CustomContentProvider extends ContentProvider {

    private SharedPreferences mPref;

    public CustomContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mPref = getContext().getSharedPreferences(MainActivity.BROADCAST_TYPE, Context.MODE_PRIVATE);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[] {
                "key", "value"
        });

        Map all = mPref.getAll();
        Object value;
        for (String key : selectionArgs) {
            if (all.containsKey(key)) {
                value = all.get(key);
                cursor.addRow(new Object[] {
                        key, value
                });
            }
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
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
