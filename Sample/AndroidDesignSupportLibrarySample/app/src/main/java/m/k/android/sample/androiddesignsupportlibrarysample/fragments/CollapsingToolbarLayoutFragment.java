package m.k.android.sample.androiddesignsupportlibrarysample.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m.k.android.sample.androiddesignsupportlibrarysample.R;

public class CollapsingToolbarLayoutFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextInputLayoutFragment.
     */
    public static CollapsingToolbarLayoutFragment newInstance() {
        CollapsingToolbarLayoutFragment fragment = new CollapsingToolbarLayoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CollapsingToolbarLayoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collapsing_toolbar_layout, container, false);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("ToolBar");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        TextView text = (TextView)view.findViewById(R.id.text);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<100; i++) {
            sb.append("Text").append(i).append("\n");
        }
        text.setText(sb.toString());

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
