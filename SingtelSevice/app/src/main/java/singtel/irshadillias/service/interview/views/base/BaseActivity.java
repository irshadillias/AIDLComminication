package singtel.irshadillias.service.interview.views.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import singtel.irshadillias.service.interview.R;
import singtel.irshadillias.service.interview.utilities.ViewUtility;


public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    @SuppressWarnings("unused")
    public D dataBinding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    public static void replaceFragment(AppCompatActivity activity, Fragment fragment, int id,
                                       boolean addToBackStack,
                                       @ViewUtility.FragmentAnimation int animationType) {

        if(null == activity)
            return;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (animationType){
            case ViewUtility.TRANSITION_POP:
                transaction.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit,
                        R.anim.anim_pop_enter, R.anim.anim_pop_exit);
                break;
            case ViewUtility.TRANSITION_FADE_IN_OUT:
                transaction.setCustomAnimations(R.anim.anim_frag_fade_in, R.anim.anim_frag_fade_out);
                break;
            case ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT:
                transaction.setCustomAnimations(R.anim.slide_in_from_rigth, R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                break;
            case ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT:
                transaction.setCustomAnimations(R.anim.slide_in_from_rigth, 0);
                break;

            case ViewUtility.TRANSITION_NONE:
            default:
                transaction.setCustomAnimations(0,0);
                break;
        }

        if (addToBackStack)
            transaction.addToBackStack(fragment.getClass().getCanonicalName());

        transaction.replace(id, fragment, fragment.getClass().getCanonicalName());
        transaction.commit();
    }


}

