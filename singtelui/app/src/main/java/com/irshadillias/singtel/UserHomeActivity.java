package com.irshadillias.singtel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.irshadillias.singtel.fragments.FragmentCalculator;
import com.irshadillias.singtel.fragments.FragmentJsonConvertor;
import com.irshadillias.singtel.utility.ViewUtility;
import com.irshadillias.singtelservice.IConvertJson;
import com.irshadillias.singtelservice.IConvertJsonLister;

import java.util.IllegalFormatConversionException;

public class UserHomeActivity extends AppCompatActivity  {

    public IConvertJson convertJson;
    private String Tag = "Client Application";
    private String serverAppUri = "singtel.irshadillias.service.interview";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.josnconvertor : {
                        FragmentJsonConvertor jsonFragment = FragmentJsonConvertor.newInstance();
                        openConvertor();
                        return true;
                    }
                    case R.id.calculator :   {
                        FragmentCalculator calFragment = FragmentCalculator.newInstance();
                        openCalaculator();
                        return true;
                    }
                }
                return false;
            }
        });
        replaceFragment(this, FragmentJsonConvertor.newInstance(), R.id.container,
                false, ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT);

      /*  num1 = findViewById(R.id.num1);
        num2 =  findViewById(R.id.num2);
        btnAdd =  findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        total = findViewById(R.id.total);*/


        initConnection();
    }
    private void openConvertor(){
        replaceFragment(this, FragmentJsonConvertor.newInstance(), R.id.container,
                false, ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT);
    }

    private void openCalaculator(){
        replaceFragment(this, FragmentCalculator.newInstance(), R.id.container,
                false, ViewUtility.TRANSITION_SLIDE_LEFT_RIGHT

        );
    }
    private void initConnection() {

        if (convertJson == null) {
            Intent intent = new Intent(IConvertJson.class.getName());

            /*this is service name*/
            intent.setAction("service.calc");

            /*From 5.0 annonymous intent calls are suspended so replacing with server app's package name*/
            intent.setPackage("singtel.irshadillias.service.interview");

            // binding to remote service
            bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(Tag, "Service Connected");
            convertJson = IConvertJson.Stub.asInterface((IBinder) iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(Tag, "Service Disconnected");
            convertJson = null;

        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);

    }

    public boolean appInstalledOrNot() {
        String uri = serverAppUri;
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (convertJson == null) {
            initConnection();
        }
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
