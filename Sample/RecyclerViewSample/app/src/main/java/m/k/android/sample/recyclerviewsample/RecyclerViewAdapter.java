package m.k.android.sample.recyclerviewsample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kishimotomasashi on 2015/06/26.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;

    private ViewHolder.IViewClickListener mListener;

    public RecyclerViewAdapter(@Nullable Context context, ViewHolder.IViewClickListener listener, List<String> dataList) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
        mDataList = dataList != null ? dataList : new ArrayList<String>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_recyclerview_item, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public String getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * http://stackoverflow.com/questions/24885223/why-doesnt-recyclerview-have-onitemclicklistener-and-how-recyclerview-is-dif/24933117#24933117
     */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.text)
        TextView mText;

        public IViewClickListener mListener;

        public ViewHolder(View view, IViewClickListener listener) {
            super(view);
            ButterKnife.inject(this, view);

            mListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null) {
                mListener.onClick(v, getAdapterPosition());
            }
        }

        public interface IViewClickListener {
            void onClick(View view, int position);
        }
    }
}
