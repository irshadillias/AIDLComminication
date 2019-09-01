package com.irshadillias.singtel.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.irshadillias.singtel.R;
import com.irshadillias.singtel.UserHomeActivity;
import com.irshadillias.singtelservice.IConvertJsonLister;

public class FragmentJsonConvertor extends Fragment implements View.OnClickListener{
    private AppCompatEditText num1, num2;
    private AppCompatButton btnAdd, btnNonPremitive, btnCall;
    private AppCompatTextView total;
    private String Tag = "Client Json";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public static FragmentJsonConvertor newInstance() {
        Bundle args = new Bundle();
        FragmentJsonConvertor fragment = new FragmentJsonConvertor();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jsonconvertor, container, false);
        num1 = view.findViewById(R.id.data);
        btnAdd =  view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        if (((UserHomeActivity)getActivity()).appInstalledOrNot()) {
            switch (view.getId()) {

                case R.id.btnAdd:
                    if (num1.length() > 0 && ((UserHomeActivity)getActivity()).convertJson != null) {
                        try {
                            IConvertJsonLister.Stub callback = new IConvertJsonLister.Stub() {
                                @Override
                                public void success(final String txt) throws RemoteException {
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            showResult(txt);
                                            Toast.makeText(getContext(),txt,Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            };
                            ((UserHomeActivity)getActivity()).convertJson .convertJson(num1.getText().toString(),callback);

                        } catch (RemoteException e) {
                            e.printStackTrace();
                            Log.d(Tag, "Connection cannot be establish");
                        }
                    }
                    break;


            }
        } else {
            Toast.makeText(getContext(), "Server App not installed", Toast.LENGTH_SHORT).show();
        }
    }



    private void showResult(String myJson){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Json Result : "+myJson);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
