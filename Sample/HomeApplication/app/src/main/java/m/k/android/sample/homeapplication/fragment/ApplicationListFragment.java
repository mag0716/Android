package m.k.android.sample.homeapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import m.k.android.sample.homeapplication.R;

public class ApplicationListFragment extends Fragment {

    @InjectView(R.id.grid)
    GridView mGrid;

    private List<ResolveInfo> mApplicationInfoList;

    public ApplicationListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        final PackageManager packageManager = activity.getPackageManager();
        mApplicationInfoList = getApplicationList(packageManager);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Adapter adapter = new Adapter(getActivity(), R.layout.application_list_item);
        adapter.addAll(mApplicationInfoList);
        mGrid.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private List<ResolveInfo> getApplicationList(@NonNull PackageManager manager) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // see PackageManager.MATCH_DEFAULT_ONLY
        return manager.queryIntentActivities(intent, 0);
    }

    public static class Adapter extends ArrayAdapter<ResolveInfo> {

        static class ViewHolder {
            @InjectView(R.id.icon)
            ImageView mIcon;
            @InjectView(R.id.label)
            TextView mLabel;

            public ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }

        private int mLayoutResourceId;
        private final PackageManager mManager;

        public Adapter(Context context, @LayoutRes int resource) {
            super(context, resource);
            mLayoutResourceId = resource;
            mManager = context.getPackageManager();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            final ResolveInfo info = getItem(position);
            holder.mIcon.setImageDrawable(info.loadIcon(mManager));
            holder.mLabel.setText(info.loadLabel(mManager));

            return convertView;
        }
    }
}
