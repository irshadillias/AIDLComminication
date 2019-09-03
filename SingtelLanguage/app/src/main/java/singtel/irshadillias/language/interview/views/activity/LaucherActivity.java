package singtel.irshadillias.language.interview.views.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.ice.restring.Restring;
import com.opencsv.CSVReader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;
import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.common.Constants;
import singtel.irshadillias.language.interview.databinding.ActivityMainBinding;
import singtel.irshadillias.language.interview.utilities.BaseUtils;
import singtel.irshadillias.language.interview.utilities.DownloadTask;
import singtel.irshadillias.language.interview.utilities.ViewUtility;
import singtel.irshadillias.language.interview.views.base.BaseActivity;
import singtel.irshadillias.language.interview.views.fragment.SigtelServerHome;
import singtel.irshadillias.language.interview.views.fragment.SingtelfragmentArticle;

public class LaucherActivity extends BaseActivity<ActivityMainBinding> implements EasyPermissions.PermissionCallbacks{
    private long downloadID;
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

    @Override
    protected void onStart() {
        super.onStart();
        if (!isFileAvailable()){
            downloadcsv();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, LaucherActivity.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //Download the file once permission is granted
        new DownloadTask(LaucherActivity.this, Constants.csvfileUrl);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(LaucherActivity.class.getSimpleName(), "Permission has been denied");
    }


    public boolean isFileAvailable(){
        boolean isfileExsist =false;
        File directory = getApplicationContext().getFilesDir();
        File file = new File(directory, Constants.filename);
        if(file.exists())
             isfileExsist = true;
        return isfileExsist;

    }
    public void downloadcsv(){
        if (BaseUtils.isSDCardPresent()) {
            //check if app has permission to write to the external storage.
            if (EasyPermissions.hasPermissions(LaucherActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Get the URL entered
                new DownloadTask(LaucherActivity.this, Constants.csvfileUrl);

            } else {
                //If permission is not present request for the same.
                EasyPermissions.requestPermissions(LaucherActivity.this, getString(R.string.write_file), Constants.WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }


        } else {

            Toast.makeText(getApplicationContext(),
                    "SD Card not found", Toast.LENGTH_LONG).show();

        }
    }



    private void beginDownload(){
        File file=new File(getExternalFilesDir(null),"Dummy");
        /*
        Create a DownloadManager.Request with all the information necessary to start the download
         */
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(Constants.csvfileUrl))
                .setTitle("Dummy File")// Title of the Download Notification
                .setDescription("Downloading")// Description of the Download Notification
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                .setDestinationUri(Uri.fromFile(file))// Uri of the destination file
                //.setRequiresCharging(false)// Set if charging is required to begin the download
                .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                .setAllowedOverRoaming(true);// Set if download is allowed on roaming network
        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager.enqueue(request);// enqueue puts the download request in the queue.
    }

}
