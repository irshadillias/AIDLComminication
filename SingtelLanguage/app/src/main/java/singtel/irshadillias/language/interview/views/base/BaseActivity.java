package singtel.irshadillias.language.interview.views.base;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


import com.ice.restring.Restring;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.SingtelApp;
import singtel.irshadillias.language.interview.utilities.BaseUtils;
import singtel.irshadillias.language.interview.utilities.LocaleManager;
import singtel.irshadillias.language.interview.utilities.ViewUtility;


public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity
        implements HasSupportFragmentInjector {
    private static final String TAG = "BaseActivity";
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

    private void showResourcesInfo() {
        Resources topLevelRes = BaseUtils.getTopLevelResources(this);
        //updateInfo("Top level  ", findViewById(R.id.tv1), topLevelRes);

        Resources appRes = getApplication().getResources();
        //updateInfo("Application  ", findViewById(R.id.tv2), appRes);

        Resources actRes = getResources();
        //updateInfo("Activity  ", findViewById(R.id.tv3), actRes);

        //TextView tv4 = findViewById(R.id.tv4);
        //String defLanguage = Locale.getDefault().getLanguage();
        //tv4.setText(String.format("Locale.getDefault() - %s", defLanguage));
        //tv4.setCompoundDrawablesWithIntrinsicBounds(null, null, getLanguageDrawable(defLanguage), null);
    }

    private void updateInfo(String title, TextView tv, Resources res) {
        Locale l = LocaleManager.getLocale(res);
        tv.setText(title + BaseUtils.hexString(res) + String.format(" - %s", l.getLanguage()));
        Drawable icon = getLanguageDrawable(l.getLanguage());
        tv.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    private Drawable getLanguageDrawable(String language) {
        switch (language) {
            case LocaleManager.LANGUAGE_ENGLISH:
                return ContextCompat.getDrawable(this, R.drawable.language_en);
            case LocaleManager.LANGUAGE_INDONESIA:
                return ContextCompat.getDrawable(this, R.drawable.language_uk);
            case LocaleManager.LANGUAGE_SPANISH:
                return ContextCompat.getDrawable(this, R.drawable.language_uk);
            default:
                Log.w(TAG, "Unsupported language");
                return null;
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(SingtelApp.localeManager.setLocale(base));
        //super.attachBaseContext(Restring.wrapContext(base));
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        BaseUtils.resetActivityTitle(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showResourcesInfo();
    }
}

