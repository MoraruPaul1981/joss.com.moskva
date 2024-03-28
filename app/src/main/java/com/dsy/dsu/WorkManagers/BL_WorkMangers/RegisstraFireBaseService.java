package com.dsy.dsu.WorkManagers.BL_WorkMangers;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceOneSignalForFirebase;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class RegisstraFireBaseService {

Context context;

    public @Inject RegisstraFireBaseService(@NotNull Context context) {
        this.context = context;
    }

    public void МетодРегистрацииУстройсвоНАFirebaseAndOneSignal( ) {
        try{
            Intent intentstartServiceOneSignal=new Intent(context, ServiceOneSignalForFirebase.class);
            intentstartServiceOneSignal.setAction("com.registariionesignal.net");
            // TODO: 28.03.2024  
          context.  startService(intentstartServiceOneSignal);

            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,0); " +
                    " РезультатЗаписиНовогоIDОтСервреаOneSignal  " );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



}
