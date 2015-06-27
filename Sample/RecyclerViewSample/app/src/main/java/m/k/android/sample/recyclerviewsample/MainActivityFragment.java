package m.k.android.sample.recyclerviewsample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @InjectView(R.id.listview)
    ListView mListView;
    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private static final List<String> DATA = new ArrayList<>();

    static {
        for(int i=0; i<100; i++) {
            DATA.add(String.format("DATA%d", i));
        }
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_recyclerview, container, false);

        ButterKnife.inject(this, view);

        mListView.setAdapter(new ListViewAdapter(getActivity(), DATA));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        "ListView#selected : " + parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), DATA));

        return view;
    }
}
