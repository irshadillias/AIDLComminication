package singtel.irshadillias.language.interview.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ice.restring.Restring;

import java.util.HashMap;
import java.util.Map;

import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.databinding.ActivityMainBinding;
import singtel.irshadillias.language.interview.utilities.ViewUtility;
import singtel.irshadillias.language.interview.views.base.BaseActivity;
import singtel.irshadillias.language.interview.views.fragment.SigtelServerHome;
import singtel.irshadillias.language.interview.views.fragment.SingtelfragmentArticle;

public class LaucherActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.josnconvertor : {
                        openHome();
                        return true;
                    }
                    case R.id.calculator :   {
                        openArticle();
                        return true;
                    }
                }
                return false;
            }
        });
        replaceFragment(this, SigtelServerHome.newInstance(), R.id.fragContainer,
                false, ViewUtility.TRANSITION_NONE);
    }

    private void openHome(){
        replaceFragment(this, SigtelServerHome.newInstance(), R.id.fragContainer,
                false, ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT);
    }

    private void openArticle(){
        replaceFragment(this, SingtelfragmentArticle.newInstance(), R.id.fragContainer,
                false, ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT

        );
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
