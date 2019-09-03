package singtel.irshadillias.language.interview.utilities;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.ice.restring.Restring;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import singtel.irshadillias.language.interview.R;
import singtel.irshadillias.language.interview.common.Constants;

public class DownloadTask {
    private static final String TAG = "Download Task";
    private Context context;

    private String downloadUrl = "", downloadFileName = "";
    private ProgressDialog progressDialog;
    private String mCurrentFileName ;
    private String mCurrentFilePath;

    public DownloadTask(Context context, String downloadUrl) {
        this.context = context;

        this.downloadUrl = downloadUrl;


        downloadFileName = downloadUrl.substring(downloadUrl.lastIndexOf('/'), downloadUrl.length());//Create file name by picking download file name from URL
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingTask().execute();
    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Downloading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    progressDialog.dismiss();
                    Toast.makeText(context,"sucesss"+outputFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
                    readFileUpdatestring(outputFile.getAbsolutePath());
                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();

                //Change button text if exception occurs

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }

                outputFile = createFile();


                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }
    }
    private File createFile() throws IOException{
        File storagedir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File mFile      = File.createTempFile(Constants.filename, ".csv",storagedir);
        return mFile;
    }

    private void readFileUpdatestring(String path){
        try {

            CSVReader reader = new CSVReader(new FileReader(path));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                Map<String, String> map = new HashMap<>();
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
                map.put(nextLine[1],nextLine[2]);
                Restring.setStrings(nextLine[0], map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }
    }
}
