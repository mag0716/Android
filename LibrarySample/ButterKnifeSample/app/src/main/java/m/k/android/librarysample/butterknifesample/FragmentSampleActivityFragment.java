package m.k.android.librarysample.butterknifesample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentSampleActivityFragment extends Fragment {

    @InjectView(R.id.text)
    TextView mText;
    @InjectView(R.id.btn)
    Button mBtn;

    public FragmentSampleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_sample, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mText.setText("Update Text");
    }

    @OnClick(R.id.btn)
    public void showToast() {
        Toast.makeText(getActivity(), "Toast", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn)
    public void showToast2() {
        // もし、同じ ID の View に複数のメソッドがセットされていたら？
        // -> showToast() -> showToast2() の順で呼び出される
        Toast.makeText(getActivity(), "Toast2", Toast.LENGTH_SHORT).show();
    }

}
