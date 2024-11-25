package com.dsy.dsu.LayerDatabase.binesslogiclayer.updates;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.LayerBunessLogic.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class PublicidAfterSuccessLogin {

    Context context;

    public  @Inject PublicidAfterSuccessLogin(@ApplicationContext Context hiltcontext) {
        this.context = hiltcontext;

    }





    // TODO: 10.07.2021  только для чата
    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ

    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer wewillsetupanewPublicidaftersuccessfulsynchronizationSuccessLogin(
            @NonNull String ИмяТаблицы,
            @NonNull   ContentValues КонтейнерДляВставкиПубличныйID,
            @NonNull Integer PublicID )
            throws ExecutionException,
            InterruptedException, TimeoutException {
        // TODO: 08.10.2024
        Integer   UpdatingPublicID=0;
        try {
// TODO: 08.10.2024 Update PUBLIC ID AFTER SYNnc
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + ИмяТаблицы + "");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderNewPubicID = context.getContentResolver();
            // TODO: 08.10.2024
            КонтейнерДляВставкиПубличныйID.put("publicid",PublicID);

            // TODO: 08.10.2024 Находим если такой  Пользователь
            Long getuuidLocal=  new Class_Generations_PUBLIC_CURRENT_ID(context).gettingSettingTableVersion(context," SELECT id FROM "+ИмяТаблицы+"  ",ИмяТаблицы);
            // TODO: 08.10.2024
            КонтейнерДляВставкиПубличныйID.put("getuuidLocal",getuuidLocal);
            // TODO: 12.04.2023 UPDATER PUBLIC ID
            if(getuuidLocal>0 ){
                // TODO: 12.04.2023 UPDATER PUBLIC ID
                UpdatingPublicID=  contentProviderNewPubicID.update(uri, КонтейнерДляВставкиПубличныйID,null,null);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  UpdatingPublicID " +UpdatingPublicID);


                // TODO: 08.10.2024 UNSERT PUBLIC ID
            }else {
                // TODO: 12.04.2023 INSERT PUBLIC ID
                Uri insertData = contentProviderNewPubicID.insert(uri, КонтейнерДляВставкиПубличныйID);
                if (insertData != null) {
                    String InsertingPublicID = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                    UpdatingPublicID=Integer.parseInt(InsertingPublicID);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  InsertingPublicID " + InsertingPublicID);
                }
            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getuuidLocal " +getuuidLocal);

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return UpdatingPublicID;
    }



}
