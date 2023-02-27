package com.sous.scanner.Window;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textview.MaterialTextView;
import com.sous.scanner.Broadcastreceiver.MyDeviceAdminReceiver;
import com.sous.scanner.Errors.SubClassErrors;
import com.sous.scanner.R;

import org.jetbrains.annotations.NotNull;

import java.util.Date;


public class MainActivityNewScanner extends AppCompatActivity  {
    private String TAG;
    private Message handler;
    private NavigationBarView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewВыход;
    private BottomNavigationItemView bottomNavigationItemViewИстория;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private LinearLayout linearLayou;
    private RelativeLayout relativeLayout;
    private     Long version=0l;
    private  Message message;
    private MaterialTextView materialTextViewToolBar;
    private  MutableLiveData<Binder> event;
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
            Manifest.permission.WRITE_SECURE_SETTINGS ,
            Manifest.permission.BLUETOOTH_ADMIN})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_newscanner);
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
            relativeLayout = (RelativeLayout) findViewById(R.id.activitynain_for_Taskslinelayoutrela3);
            fragmentManager = getSupportFragmentManager();
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            Log.i(this.getClass().getName(),  "onResume " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );

            МетодHandles();
            МетодПрячемБарИКнопки();
            // TODO: 19.02.2023 разрешает обновлени BLE
            МетодРАзрешенияBlurtooTКлиент();
            // TODO: 24.02.2023 advens
            МетодРазрешенияДополнительное();
            // TODO: 25.01.2023  подключение после получение BINDER
            МетодКнопкаBackExit(new Intent("activity"));
            // TODO: 24.01.2023  переходят после получение binder
            МетодЗапускBootФрагмента(new FragmentBootScanner());//todo Запускам клиента или сервер фрагмент

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

    private void МетодРазрешенияДополнительное() {
        try {
            ComponentName componentName = new ComponentName(this, MyDeviceAdminReceiver.class);
            MyDeviceAdminReceiver myDeviceAdminReceiver=new MyDeviceAdminReceiver();
            Intent broadcastReceiver_sous_notificatioons = new Intent(this, MyDeviceAdminReceiver.class);
            registerReceiver(myDeviceAdminReceiver,new IntentFilter(),DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN, handler.getTarget());
            DevicePolicyManager  devicePolicyManager=  myDeviceAdminReceiver.getManager(getApplication());

            if ( devicePolicyManager.isAdminActive(componentName)==false) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN );
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_DELEGATION_SCOPES,componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES,componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_PROVISIONING_MODE,1);
                startActivity(intent);
                Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                        +" devicePolicyManager.isAdminActive(componentName) "+devicePolicyManager.isAdminActive(componentName));
            } else {
                Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                        +" devicePolicyManager.isAdminActive(componentName) "+devicePolicyManager.isAdminActive(componentName));
            }
         // TODO: 24.02.2023

         Process process=   Runtime.getRuntime().exec("dpm set-device-owner com.sous.scanner/.MyDeviceAdminReceiver");

            process.waitFor();
            // TODO: 24.02.2023

           if(devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())){
               Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                       +" devicePolicyManager.isAdminActive(componentName) "+devicePolicyManager.isAdminActive(componentName));
            }else{
               Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                       +" devicePolicyManager.isAdminActive(componentName) "+devicePolicyManager.isAdminActive(componentName));
           }

         //   devicePolicyManager.setDelegatedScopes();

/*

           // devicePolicyManager.setPermissionGrantState(componentName, this.getPackageName(), Manifest.permission.READ_PHONE_STATE, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
            devicePolicyManager.setPermissionGrantState(componentName, this.getPackageName(), Manifest.permission.ACCESS_FINE_LOCATION, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
            devicePolicyManager.setPermissionGrantState(componentName,this. getPackageName(), Manifest.permission.ACCESS_FINE_LOCATION, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
            devicePolicyManager.setPermissionGrantState(componentName, this.getPackageName(), Manifest.permission.ACCESS_FINE_LOCATION, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
            devicePolicyManager.setPermissionGrantState(componentName, this.getPackageName(), Manifest.permission.ACCESS_FINE_LOCATION, DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED);
*/

            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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

    public void МетодHandles() {
       handler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
           @Override
           public boolean handleMessage(@NonNull Message msg) {
               return true;
           }
       }).obtainMessage();
        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
    }
    public void МетодКнопкаBackExit(@NotNull Intent intent) {
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
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.show(fragment);
            Log.i(this.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
                    Manifest.permission.WRITE_SECURE_SETTINGS,
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
                    Manifest.permission.WRITE_SECURE_SETTINGS ,
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
            // TODO: 19.02.2023 Безконечное Посик Дивайсов РАзрешение
            Intent discoverableIntent = new Intent();
            discoverableIntent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);//BluetoothAdapter.ACTION_DISCOVERY_FINISHED
            discoverableIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(discoverableIntent);

            Log.i(this.getClass().getName(),  "НАстройки BLE " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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

   
}