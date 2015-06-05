package m.k.android.sample.androiddesignsupportlibrarysample;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((NavigationView)findViewById(R.id.navigation)).setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean ret = true;

        Toast.makeText(this, "onNavigationItemSelected : " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();

        switch(menuItem.getItemId()) {
            case R.id.navigation_item_2:
                break;
            case R.id.navigation_item_3:
                break;
            case R.id.navigation_item_4:
                break;
            case R.id.navigation_item_5:
                break;
            case R.id.navigation_item_6:
                break;
            case R.id.navigation_item_7:
                break;
            default:
                ret = false;
                break;
        }

        return ret;
    }
}
