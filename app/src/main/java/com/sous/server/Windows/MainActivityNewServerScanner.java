package com.sous.server.Windows;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textview.MaterialTextView;
import com.sous.server.Brodcasters.BroadcastReceiverWorkManagerScannersServer;
import com.sous.server.Errors.SubClassErrors;
import com.sous.server.R;
import com.sous.server.Services.BindingServices;
import com.sous.server.Services.ServiceForServerScannerAsync;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.net.SocketFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.Buffer;
import okio.ByteString;


public class MainActivityNewServerScanner extends AppCompatActivity  {
    private String TAG;
    private Message handler;
    private NavigationBarView bottomNavigationView;
    private MaterialTextView materialTextViewToolBar;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewВыход;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewИстория;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private  LinearLayout linearLayou;
    private  Long version;
    private RelativeLayout relativeLayout;
    private OkHttpClient okHttpClient;

    @SuppressLint("RestrictedApi")
    @RequiresPermission(anyOf = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.READ_BASIC_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_PRIVILEGED,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.READ_CONTACTS ,
            Manifest.permission.WRITE_CONTACTS ,
            Manifest.permission.WRITE_APN_SETTINGS ,
            Manifest.permission.RECEIVE_SMS ,
            Manifest.permission.MODIFY_PHONE_STATE ,
            Manifest.permission.BLUETOOTH_ADMIN})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_newserverscanner);
            TAG = getClass().getName().toString();
            getSupportActionBar().hide(); ///скрывать тул бар
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            // TODO: 08.12.2022
            bottomNavigationView = (NavigationBarView) findViewById(R.id.BottomNavigationViewScanner);
            bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
            materialTextViewToolBar=( MaterialTextView)  findViewById(R.id.text_scanner_work);
            // TODO: 05.12.2022 строчлочки
            bottomNavigationItemViewВыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewИстория = bottomNavigationView.findViewById(R.id.id_scanner_history);
            bottomNavigationItemViewВыход.setItemRippleColor(ColorStateList.valueOf(Color.RED));
            bottomNavigationItemViewИстория.setItemRippleColor(ColorStateList.valueOf(Color.RED));
            linearLayou = (LinearLayout) findViewById(R.id.activity_main_newscanner);
            relativeLayout = (RelativeLayout) findViewById(R.id.relativelayoutserverble);
            fragmentManager = getSupportFragmentManager();
            PackageInfo pInfo =getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            Log.i(this.getClass().getName(),  "  "
                    +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString()) ;
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString()) ;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }


    @SuppressLint("RestrictedApi")
    @Override
    protected void onStart() {
        super.onStart();
        try {
            // TODO: 15.03.2023
            МетодРАзрешенияBlurtooTКлиент();
            МетодВызовИСозданиеClentSocket();
            // TODO: 07.02.2023 дополнительное разрещения blutoon
            МетодПрячемБарИКнопки();
            МетодСобыытиеКнопокСканирования(new Intent("activity"));
            ОтветныйHendlerОтСлужбы();
            МетодЗапускаетBroadcast();
            // TODO: 07.02.2023 запус самого СЕРВЕРА СКАНРРОВНИЕ..
            МетодЗапускBootФрагмента(new FragmentBootServer());//todo Запускам клиента или сервер фрагмент

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }
    private void МетодВызовИСозданиеClentSocket() throws URISyntaxException, IOException {
        Log.i(this.getClass().getName(),  "  "
                +Thread.currentThread().getStackTrace()[2].getMethodName()+
                " время " +new Date().toLocaleString());
        okHttpClient = new OkHttpClient();
         final class SocketClients extends WebSocketListener {

             public SocketClients() {
                 super();
             }

             @Override
             public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                 super.onClosed(webSocket, code, reason);
             }

             @Override
             public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                 super.onClosing(webSocket, code, reason);
             }

             @Override
             public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                 super.onFailure(webSocket, t, response);
             }

             @Override
             public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                 super.onMessage(webSocket, text);
             }

             @Override
             public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                 super.onMessage(webSocket, bytes);
             }

             @Override
             public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                 super.onOpen(webSocket, response);
                 webSocket.send("ddd");
             }
         }
        // TODO: 04.03.2023 второй вариант
       /* URI url = new URI("ws://192.168.207.84:8080/");
        SocketFactory webSocket=okHttpClient.socketFactory();
        Socket socket=webSocket.createSocket("",8000);
        socket.*/
        // TODO: 04.03.2023 wesocket

       Request request=new Request.Builder().url("ws://websocket.org").build();
      WebSocket webSocket= okHttpClient.newWebSocket(request,new SocketClients());
        okHttpClient.dispatcher().executorService().shutdown();
       // WebSocket webSocketandroid=okHttpClient.n;
    }


    public void МетодПрячемБарИКнопки() {
        bottomNavigationView.setVisibility(View.INVISIBLE);
        materialTextViewToolBar.setVisibility(View.INVISIBLE);
        relativeLayout.refreshDrawableState();
        relativeLayout.forceLayout();
        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
    }

    public void МетодВидимыеПрограссБарИКнопки() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        materialTextViewToolBar.setVisibility(View.VISIBLE);
        relativeLayout.refreshDrawableState();
        relativeLayout.forceLayout();
        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
    }

    public void МетодСобыытиеКнопокСканирования(@NotNull Intent intent) {
        try {
            bottomNavigationItemViewВыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), " bottomNavigationView " + bottomNavigationView);
                    if (intent.getAction().equalsIgnoreCase("fragment")) {
                        finishAndRemoveTask();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // TODO: 05.12.2022  запуск клиента
    @SuppressLint("SuspiciousIndentation")
    protected void МетодЗапускBootФрагмента(@NonNull Fragment fragment) {
        try {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.replace(R.id.framelauoutScanner, fragment);//.layout.activity_for_fragemtb_history_tasks
                // fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();//FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.show(fragment);
// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данны
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }

    private void МетодРАзрешенияBlurtooTКлиент() {
        try{
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    /*  Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,*/
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_PRIVILEGED,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.READ_BASIC_PHONE_STATE,
                    Manifest.permission.READ_PRECISE_PHONE_STATE,
                    Manifest.permission.READ_CONTACTS ,
                    Manifest.permission.WRITE_CONTACTS ,
                    Manifest.permission.WRITE_APN_SETTINGS ,
                    Manifest.permission.RECEIVE_SMS ,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.MODIFY_PHONE_STATE,
            };
            String[] PERMISSIONS_LOCATION = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    /*    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,*/
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_PRIVILEGED,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_PRECISE_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.READ_BASIC_PHONE_STATE,
                    Manifest.permission.READ_CONTACTS ,
                    Manifest.permission.WRITE_CONTACTS ,
                    Manifest.permission.WRITE_APN_SETTINGS ,
                    Manifest.permission.RECEIVE_SMS ,
                    Manifest.permission.MODIFY_PHONE_STATE ,
                    Manifest.permission.SEND_SMS
            };
            int permission1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permission2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
            if (permission1 != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        this,
                        PERMISSIONS_STORAGE,
                        1
                );
            } else if (permission2 != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                        this,
                        PERMISSIONS_LOCATION,
                        1
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }



    private void ОтветныйHendlerОтСлужбы() {
        try {
            handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(TAG, "onCreate: msg " + msg);
                    return true;
                }
            }).obtainMessage();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }



    public void МетодЗапускаетBroadcast() {
        try {
            BroadcastReceiverWorkManagerScannersServer broadcastReceiverWorkManagerScannersServer= new BroadcastReceiverWorkManagerScannersServer();
            Intent ИнтретПоЗапускуПовторноШироковещательногоСинхрониазции =
                    new Intent(getApplicationContext()
                    , BroadcastReceiverWorkManagerScannersServer.class);
            ИнтретПоЗапускуПовторноШироковещательногоСинхрониазции.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ИнтретПоЗапускуПовторноШироковещательногоСинхрониазции.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            registerReceiver(broadcastReceiverWorkManagerScannersServer,new IntentFilter(), "android.intent.category.DEFAULT",handler.getTarget());
            getApplicationContext().sendBroadcast(ИнтретПоЗапускуПовторноШироковещательногоСинхрониазции);
            Log.d(getApplicationContext().getClass().getName(), " ПРОШЕЛ ЗАПУСК     " +
                    " BroadcastReceiver_Sous_Asyns_Glassfish  broadcastReceiver_sous_asyns_glassfish= new BroadcastReceiver_Sous_Asyns_Glassfish(); " );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки=new ContentValues();
            valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы","ErrorDSU1");
            valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }
}