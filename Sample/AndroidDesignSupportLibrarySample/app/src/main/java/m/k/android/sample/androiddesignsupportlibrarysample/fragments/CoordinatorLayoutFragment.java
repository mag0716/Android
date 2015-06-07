package m.k.android.sample.androiddesignsupportlibrarysample.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import m.k.android.sample.androiddesignsupportlibrarysample.R;

public class CoordinatorLayoutFragment extends Fragment implements View.OnClickListener {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextInputLayoutFragment.
     */
    public static CoordinatorLayoutFragment newInstance() {
        CoordinatorLayoutFragment fragment = new CoordinatorLayoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mBtn1;
    private FloatingActionButton mBtn2;

    public CoordinatorLayoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_coordinator_layout, container, false);

        mCoordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator_layout);
        mBtn1 = (FloatingActionButton)view.findViewById(R.id.btn1);
        mBtn2 = (FloatingActionButton)view.findViewById(R.id.btn2);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        final int id = v.getId();

        switch(id) {
            case R.id.btn1:
            case R.id.btn2:
                Snackbar.make(mCoordinatorLayout, "SnackBar message", Snackbar.LENGTH_SHORT)
                        .show();
                break;
        }
    }
}
