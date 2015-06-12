package m.k.android.sample.androiddesignsupportlibrarysample.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import m.k.android.sample.androiddesignsupportlibrarysample.R;

public class SnackBarFragment extends Fragment implements View.OnClickListener {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TextInputLayoutFragment.
     */
    public static SnackBarFragment newInstance() {
        SnackBarFragment fragment = new SnackBarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private FrameLayout mParent;
    private TextView mText;

    public SnackBarFragment() {
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
        View view = inflater.inflate(R.layout.fragment_snack_bar, container, false);

        mParent = (FrameLayout)view.findViewById(R.id.container);
        mText = (TextView)view.findViewById(R.id.text);
        view.findViewById(R.id.btn).setOnClickListener(this);

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
        mText.setText("show SnackBar.");
        Snackbar.make(mParent, "SnackBar message", Snackbar.LENGTH_SHORT)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mText.setText("Clicked.");
                    }
                })
                // SnackBar custom.
                .setActionTextColor(Color.RED)
                .show();
    }
}
