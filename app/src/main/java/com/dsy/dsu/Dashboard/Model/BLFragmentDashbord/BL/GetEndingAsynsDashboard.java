package com.dsy.dsu.Dashboard.Model.BLFragmentDashbord.BL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusEndAync;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

public class GetEndingAsynsDashboard {



    public void metoEndingAsynsDashboard(@NonNull Context context,
                                         @NonNull ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО){
        try {

            Intent intentAnsycEnding=new Intent("AnsycEnding");
            intentAnsycEnding.setAction("Broad_messageAsyncOrUpdatePO");
            Bundle bundle=new Bundle();

            bundle.putBinder("callbackbinderdashbord",localBinderОбновлениеПО);
            bundle.putString("Статус",   "AnsycEnding");///"В процесс"
            intentAnsycEnding.putExtras(bundle);

            EventBus.getDefault().post(new MessageEvensBusEndAync(intentAnsycEnding));

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}
