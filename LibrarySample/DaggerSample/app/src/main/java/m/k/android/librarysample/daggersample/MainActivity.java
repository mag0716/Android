package m.k.android.librarysample.daggersample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import dagger.ObjectGraph;


public class MainActivity extends ActionBarActivity {

    @Inject
    IDebugger mDebugger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectGraph objectGraph = ObjectGraph.create(new DebuggerModule(this));
        objectGraph.inject(this);
    }

    public void onClick(View view) {
        mDebugger.showDebugMessage("Dagger Sample!!");
    }
}
