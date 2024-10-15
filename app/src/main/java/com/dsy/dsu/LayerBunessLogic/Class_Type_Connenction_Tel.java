package com.dsy.dsu.LayerBunessLogic;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

public class Class_Type_Connenction_Tel    {
    Context contextДляОпределенияКакойТИмПодключения=null;
    Activity activityКакойТИмПодключения;
    public Class_Type_Connenction_Tel(Context context) {
        contextДляОпределенияКакойТИмПодключения=context;
        //
    }
    public String МетодОпределяемКакойТипПодключениеWIFIилиMobile() {

        String КакойТипПодключения = new String();
        //
        try {

            // TODO: 02.09.2021  проверяем какое подключение
            //todo  сам код

            ConnectivityManager connManager = (ConnectivityManager) contextДляОпределенияКакойТИмПодключения.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (mWifi.isConnected()) {
                // Do whatever

                КакойТипПодключения="WIFI";

                Log.d(Class_MODEL_synchronized.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
            }else{
                // TODO: 29.09.2021
                NetworkInfo mMOBILE = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if (mMOBILE.isConnected()) {
                    // Do whatever
                    КакойТипПодключения="Mobile";
                    Log.d(Class_MODEL_synchronized.class.getName()," КакойТипПодключения"+ КакойТипПодключения);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(contextДляОпределенияКакойТИмПодключения).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return КакойТипПодключения;
    }


}
