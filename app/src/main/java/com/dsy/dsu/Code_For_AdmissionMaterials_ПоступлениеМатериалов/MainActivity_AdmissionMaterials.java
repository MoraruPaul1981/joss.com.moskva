package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.AllboundServices.AllBindingService;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;

import java.util.Date;

public class MainActivity_AdmissionMaterials extends AppCompatActivity {
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_ДляПолучениеМатериалов;
    private LinearLayout activity_admissionmaterias_face ;

  private  BusinessLogic businessLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_admission_materials);
        activity=this;
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().hide(); ///скрывать тул бар
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
           activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_mainface);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials");


            //////////////////////TODO SERVICE
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.READ_PHONE_STATE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.VIBRATE,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                    android.Manifest.permission.INSTALL_PACKAGES,
                    android.Manifest.permission.WRITE_SETTINGS,
                    android. Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(this, permissions, 1);



            businessLogic=new BusinessLogic();
            // TODO: 04.11.2022 test
            businessLogic. МетодЗапускФрагментаПриемМатериалов();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).
                МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(),
                        Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



// TODO: 27.07.2023 class business logoc

    class BusinessLogic{
        protected void МетодЗапускФрагментаПриемМатериалов() {
            try{
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                fragment_ДляПолучениеМатериалов = new FragmentAdmissionMaterials();
                fragmentTransaction.setPrimaryNavigationFragment(fragment_ДляПолучениеМатериалов);

                String FragmentNewImageName=   fragment_ДляПолучениеМатериалов.getClass().getName();

                fragmentTransaction.addToBackStack(FragmentNewImageName);

                fragmentTransaction.add(R.id.activity_admissionmaterias_mainface, fragment_ДляПолучениеМатериалов);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.commit();
                fragmentTransaction.show(fragment_ДляПолучениеМатериалов);
                Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов " + fragment_ДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).
                        МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        protected void МетодOnBackStackChangedListenerФрагментаПриемМатериалов() {
            try{
                int backStackEntryCount = fragmentManager.getBackStackEntryCount();
                FragmentManager.BackStackEntry fragment = fragmentManager .getBackStackEntryAt(backStackEntryCount - 1);
                      Fragment fragmentBaclStack=          fragmentManager.getFragments().get(  fragment.getId());
                fragmentBaclStack.onResume();
                Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов " + fragment_ДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).
                        МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }
}