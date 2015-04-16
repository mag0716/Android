package m.k.android.librarysample.butterknifesample;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;


public class ListViewSampleActivity extends ActionBarActivity {

    private static List<String> mDataList;

    static {
        mDataList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            mDataList.add("Data" + i);
        }
    }

    @InjectView(R.id.list)
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);
        ButterKnife.inject(this);

        Adapter adapter = new Adapter(this, R.layout.list_item);
        adapter.addAll(mDataList);
        mList.setAdapter(adapter);
    }

    @OnItemClick(R.id.list)
    void showData(int position) {
        Toast.makeText(this, mList.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
    }

    public static class Adapter extends ArrayAdapter<String> {

        static class ViewHolder {
            @InjectView(R.id.text1)
            TextView mText1;
            @InjectView(R.id.text2)
            TextView mText2;

            public ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }

        private int mResourceId;

        public Adapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
            mResourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(mResourceId, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            final String data = getItem(position);
            holder.mText1.setText("Title: " + data);
            holder.mText2.setText("Sub: " + data);

            return convertView;
        }
    }
}
