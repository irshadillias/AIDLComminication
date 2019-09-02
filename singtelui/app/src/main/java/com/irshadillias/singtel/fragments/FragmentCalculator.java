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
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.irshadillias.singtel.R;
import com.irshadillias.singtel.UserHomeActivity;
import com.irshadillias.singtelservice.IConvertJsonLister;

public class FragmentCalculator extends Fragment implements View.OnClickListener{
    private String Tag = "ClientCalculation";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AppCompatEditText num1, num2;
    private Button btnAdd,btnSub,btnMulti,btnPow;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static FragmentCalculator newInstance() {
        Bundle args = new Bundle();
        FragmentCalculator fragment = new FragmentCalculator();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_calculator, container, false);
        num1 = view.findViewById(R.id.num1);
        num2 = view.findViewById(R.id.num2);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnSub = view.findViewById(R.id.btnSub);
        btnMulti = view.findViewById(R.id.btnMulti);
        btnPow   = view.findViewById(R.id.btnPow);
        btnSub.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnPow.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        if (((UserHomeActivity)getActivity()).appInstalledOrNot()) {
            if(num1.length() > 0 && num2.length() > 0 && ((UserHomeActivity)getActivity()).convertJson != null){
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
                try{
                    switch (view.getId()) {

                        case R.id.btnAdd :
                            ((UserHomeActivity)getActivity()).convertJson .addNumber(Integer.parseInt(num1.getText().toString()),Integer.parseInt(num2.getText().toString()),callback);
                            break;
                        case R.id.btnSub :
                            ((UserHomeActivity)getActivity()).convertJson .substract(Integer.parseInt(num1.getText().toString()),Integer.parseInt(num2.getText().toString()),callback);
                            break;
                        case R.id.btnMulti :
                            ((UserHomeActivity)getActivity()).convertJson .multiply(Integer.parseInt(num1.getText().toString()),Integer.parseInt(num2.getText().toString()),callback);
                            break;
                        case R.id.btnPow:
                            ((UserHomeActivity)getActivity()).convertJson .pow(Integer.parseInt(num1.getText().toString()),Integer.parseInt(num2.getText().toString()),callback);
                            break;
                    }
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(getContext(), "Server App not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult(String myJson){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Result : "+myJson);
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
