package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;

public class MainActivity_AdmissionMaterials extends AppCompatActivity {
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_ДляПолучениеМатериалов;
    private LinearLayout activity_admissionmaterias_face,activity_admissionmaterias_down;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
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
            fragmentManager = getSupportFragmentManager();
           activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_face);
         activity_admissionmaterias_down = (LinearLayout) findViewById(R.id.activity_admissionmaterias_down);
            activity_admissionmaterias_down.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
// Changes the height and width to the specified *pixels*
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);
                Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials  binder  "+binder);
            // TODO: 27.09.2022  запускаем фрагмент получение материалов
            МетодЗапускФрагментаПриемМатериалов();
            // TODO: 04.11.2022 test
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

    protected void МетодЗапускФрагментаПриемМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_ДляПолучениеМатериалов = new FragmentAdmissionMaterials();
            Bundle data=new Bundle();
            data.putBinder("binder",binder);
            fragment_ДляПолучениеМатериалов.setArguments(data);
            fragmentTransaction.add(R.id.activity_admissionmaterias_face, fragment_ДляПолучениеМатериалов);//.layout.activity_for_fragemtb_history_tasks
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



    // TODO: 02.08.2022
    void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
        try{
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
            Integer  ПубличныйIDДляАсинх=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляАсинх);
            Intent  intentЗапускОднорworkanager=new Intent();
            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            new Class_Generator_One_WORK_MANAGER(getApplicationContext()).
                    МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),intentЗапускОднорworkanager);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ПубличныйIDДляОдноразовойСинхронПубличныйIDДляФрагментаиазции "+ПубличныйIDДляФрагмента );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
    }
}