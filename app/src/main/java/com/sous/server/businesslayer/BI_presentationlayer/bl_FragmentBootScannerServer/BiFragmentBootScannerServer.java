package com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sous.server.R;
import com.sous.server.businesslayer.Errors.SubClassErrors;

public class BiFragmentBootScannerServer {

    protected  Context context;
    protected   FragmentTransaction getTransactionscanner;
    protected Long version;
    protected FragmentManager fragmentManager;

    protected Activity getactivity;

    public BiFragmentBootScannerServer(Context context, FragmentTransaction getfragmentTransaction, Activity getactivity) {
        this.context = context;
        this.getTransactionscanner = getTransactionscanner;
        this.version = version;
        this.fragmentManager = fragmentManager;
        this.getactivity = getactivity;
    }



}
