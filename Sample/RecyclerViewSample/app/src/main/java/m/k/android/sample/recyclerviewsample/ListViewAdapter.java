package m.k.android.sample.recyclerviewsample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kishimotomasashi on 2015/06/20.
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;

    public ListViewAdapter(@Nullable Context context, List<String> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList != null ? dataList : new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(R.layout.view_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.mText.setText(getItem(position).toString());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.text)
        TextView mText;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
