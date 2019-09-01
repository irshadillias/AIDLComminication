package singtel.irshadillias.service.interview.views.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import singtel.irshadillias.service.interview.R;
import singtel.irshadillias.service.interview.databinding.ActivityMainBinding;
import singtel.irshadillias.service.interview.utilities.ViewUtility;
import singtel.irshadillias.service.interview.views.base.BaseActivity;
import singtel.irshadillias.service.interview.views.fragment.SigtelServerHome;

public class LaucherActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(this, SigtelServerHome.newInstance(), R.id.fragContainer,
                false, ViewUtility.TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
