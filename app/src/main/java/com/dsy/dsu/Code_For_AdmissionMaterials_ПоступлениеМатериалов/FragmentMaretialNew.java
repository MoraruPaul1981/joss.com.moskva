package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ShareCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.util.BackgroundQueue;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;


public class FragmentMaretialNew extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСозданиеМатерила;
    private Handler handler;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentПолученыеМатериалов;

    private LinkedHashMap<String, Object> cursorConcurrentSkipListMap=new LinkedHashMap<>();
    private Animation animation;
    private Animation animationscroll;
    private  Integer ПубличныйIDДляФрагмента;
    private Cursor CursorДляОдногоМатериалаБышВесов;
    private Cursor CursorДляАвтомобиля;
    private Cursor CursorДляКонтрагента;
    private    Cursor CursorДляГруппаМатериалов;
    private   Cursor CursorДляЦФО;
    private  Object ВытаскиваемIDМатериаловИзСправочника;
    private  View view=null;
    private SharedPreferences preferencesМатериалы;
    private Boolean ФлагЧтоУжепервыйПрогоУжеПрошул=false;
    private  ScrollView scrollViewНовыйматериал;
    private  AsyncTaskLoader asyncTaskLoader;

    private   Bitmap bitmap;
    // TODO: 15.12.2022 получение материалов
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;

    private     DatePickerDialog ДатаДляКалендаря;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity().getSupportFragmentManager();
            preferencesМатериалы = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            Bundle data=         getArguments();
            binderДляПолучениеМатериалов=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");

            МетодHandlerCallBack();

            // TODO: 03.11.2022  ПОСЛЕ ПОЛУЧЕННЫХ ДАННЫХ
            Log.d(getContext().getClass().getName(), "\n" + " CursorДляЦФО "
                    + CursorДляЦФО + " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов +
                    " CursorДляАвтомобиля " + CursorДляАвтомобиля + " CursorДляКонтрагента " +CursorДляКонтрагента
                    + " CursorДляГруппаМатериалов " +CursorДляГруппаМатериалов );

            Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            bitmap=(Bitmap) data.getExtras().get("data");
            Log.d(getContext().getClass().getName(), "\n" + " CursorДляЦФО "
                    + CursorДляЦФО + " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов +
                    " CursorДляАвтомобиля " + CursorДляАвтомобиля + " CursorДляКонтрагента " +CursorДляКонтрагента
                    + " CursorДляГруппаМатериалов " +CursorДляГруппаМатериалов );
        }
        Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void методGetDataForNewFragment() {
        try{
            if (asyncTaskLoader==null || !asyncTaskLoader.isReset()) {
                asyncTaskLoader=new AsyncTaskLoader(getContext()) {
                    @Nullable
                    @Override
                    public Object loadInBackground() {
                        try{
                        Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                        // TODO: 20.10.2022 #1
                        МетодПолучениеДанныхДляЦФО(intentДляПолучениеСправочкинов);
                        // TODO: 20.10.2022 #3
                        МетодПолучениеДляГруппыМатериалов(intentДляПолучениеСправочкинов);
                        // TODO: 20.10.2022 #4
                        МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов, 0);
                        // TODO: 20.10.2022 #5 автомобили
                        МетоПолучениеДанныхДляАвтомобилей(intentДляПолучениеСправочкинов, "");
                        // TODO: 20.10.2022 #6 контргаенты
                        МетоПолучениеДанныхДляКонтрагент(intentДляПолучениеСправочкинов, "");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return new Object();
                    }

                };
                asyncTaskLoader.startLoading();
                asyncTaskLoader.forceLoad();
                asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                        asyncTaskLoader.reset();
                        onStart();
                        progressBarСозданиеМатерила.setVisibility(View.GONE);
                    }
                });
            }

            Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            view= inflater.inflate(R.layout.fragment_admission_new_material, container, false);
            Log.d(this.getClass().getName(), "  onCreateView  view   "+view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            recyclerView = view.findViewById(R.id.RecyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setShifting(false);
            bottomNavigationItemViewвыход.setIconSize(70);
            bottomNavigationItemViewвыход.setItemPosition(1);
            bottomNavigationItemViewвыход.setTitle("11");
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            progressBarСозданиеМатерила =  view.findViewById(R.id.ProgressBar);
            scrollViewНовыйматериал=  (ScrollView) view.findViewById(R.id.scrollview_new_materials);
            progressBarСозданиеМатерила.setVisibility(View.VISIBLE);
            cursorConcurrentSkipListMap.putIfAbsent("Создание Нового Материала",new Object());
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_newscanner1);
            animationscroll = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);
            // TODO: 19.10.2022 методы для фрагмета создание нового материалоа
            МетодИнициализацииRecycreView();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            МетодКпопкиЗначков();
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            методGetDataForNewFragment();
            МетоКликаПоКнопкеBack();
            МетодПерегрузкаRecyceView();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            // TODO: 19.10.2022  слушатель после получение даннных в Курсом
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодПолучениеДанныхДляЦФО(Intent intentДляПолучениеСправочкинов) {
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
        CursorДляЦФО=     МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("cfo",intent ,"ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляЦФО " + CursorДляЦФО);
    }

    private void МетодПолучениеДляГруппыМатериалов(Intent intentДляПолучениеСправочкинов) {
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
        CursorДляГруппаМатериалов=      МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("type_materials",intent, "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляГруппаМатериалов " + CursorДляГруппаМатериалов);
    }

    private Cursor МетоПолучениеДанныхДляОдногоМатериала(Intent intentДляПолучениеСправочкинов, @NonNull Integer ФильтрВесовых) {
        CursorДляОдногоМатериалаБышВесов=null;
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","материал");
        bundle.putString("ФильтрКолонок","nomen_vesov");
        bundle.putInt("ФильтрДляПоискаДляОдногоМатериалаВесовые",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляОдногоМатериалаБышВесов=          МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("nomen_vesov",intentДляПолучениеСправочкинов,
                "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов);
        return  CursorДляОдногоМатериалаБышВесов;
    }

    // TODO: 26.12.2022  автомобили
    private void МетоПолучениеДанныхДляАвтомобилей(@NonNull Intent intentДляПолучениеСправочкинов,
                                                   @NonNull String ФильтрВесовых) {
        Bundle bundle=new Bundle();
        bundle.getString("ФильтрДляПоискаАвтомобили",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляАвтомобиля= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("track",intentДляПолучениеСправочкинов,
                "ПолучениеАвтомобильДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " CursorДляАвтомобиля " + CursorДляАвтомобиля);
    }
    // TODO: 26.12.2022  Контрагент
    private void МетоПолучениеДанныхДляКонтрагент(Intent intentДляПолучениеСправочкинов,
                                                  @NonNull String ФильтрВесовых) {
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","контрагенты");
        bundle.putString("ФильтрКолонок","company");
        bundle.getString("ФильтрДляПоискаКонтрагенты",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляКонтрагента= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("company",intentДляПолучениеСправочкинов,
                "ПолучениеКонтрагентовДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " \n" + "CursorДляКонтрагента " + CursorДляКонтрагента);
    }


    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетоКликаПоКнопкеBack(){
        try{
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        handler.postDelayed(()->{
                            методBackToFragmentAdmissionMaterilas(v);
                            Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов " + fragmentПолученыеМатериалов);
                        },50);
                        Log.d(this.getClass().getName(), "  v  " + v);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }}
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методBackToFragmentAdmissionMaterilas(@NonNull View v) {
        try{
            // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
            МетодЗапускаАнимацииКнопок(v);//todo только анимауия
            Fragment      fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
            Bundle bundleСозданиеНовогоМатериала=new Bundle();
            bundleСозданиеНовогоМатериала.putBinder("binder",binderДляПолучениеМатериалов);
            fragmentПолученыеМатериалов.setArguments(bundleСозданиеНовогоМатериала);
            fragmentTransaction = fragmentManager.beginTransaction();
            //    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
            fragmentTransaction.show(fragmentПолученыеМатериалов);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



    @Override
    public void onStop() {
        super.onStop();
        try{
            if (myRecycleViewAdapter.cursorConcurrentSkipListMap !=null) {
                myRecycleViewAdapter.notifyDataSetChanged();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
    private void МетодИнициализацииRecycreView() {
        try{
            Log.d(this.getClass().getName(), " recyclerView  "+recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorConcurrentSkipListMap);
            recyclerView.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 02.08.2022
    protected   Cursor МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала(@NonNull String  ФлагКакаяТаблицаОбработки, @NonNull Intent intent,@NonNull String ФлагКакаяРаботаНужнаДляВыполнения){
        Cursor cursor = null;
        try{
            ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=intent.getExtras();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакаяРаботаНужнаДляВыполнения);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    // TODO: 15.10.2022
                    Log.d(this.getClass().getName(), "   cursor " + cursor);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }


    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView textViewcfo,marerialtextgroupmaterial,materialtext_onematerial_ves,valueavtomobil,valuekontragent;
        private TextInputEditText textipputcountassinmaterail,textipputmaretialttn;
        private MaterialTextView textviewmaterialttn ,textviewmaterialttndata;
        private MaterialButton bottomcreateassionmaterial;

        private ArrayAdapter<String> АдапетерЦФО;
        private AlertDialog alertDialog;
        private    ListView  listViewДляЦФО =null;
        private  Cursor cursorДляВсехМатериалов;
        private  TextView textipputmaretialttdata;

        private ImageView im1,im2,im3,im4,bottom_create_image;
        // TODO: 28.10.2022
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                if(!asyncTaskLoader.isStarted()){
                    МетодИнициализацииНовогоМатериалаCardView(itemView);
                    Log.d(this.getClass().getName(), "   itemView   " + itemView);
                }
                Log.d(this.getClass().getName(), "   itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void МетодИнициализацииНовогоМатериалаCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                textViewcfo=itemView.findViewById(R.id.textViewcfo);
               TextView   textviewnamecfo=itemView.findViewById(R.id.textviewnamecfo);

                marerialtextgroupmaterial=itemView.findViewById(R.id.marerialtextgroupmaterial);
                materialtext_onematerial_ves =itemView.findViewById(R.id.materialtext_onematerial_ves);
                textipputcountassinmaterail = itemView.findViewById(R.id.textipputcountassinmaterail);
                valueavtomobil = itemView.findViewById(R.id.valueavtomobil);
                valuekontragent = itemView.findViewById(R.id.valuekontragent);
                bottomcreateassionmaterial = itemView.findViewById(R.id.bottomcreateassionmaterial);
                // TODO: 20.07.2023  кнопка     добавление Image
                bottom_create_image = itemView.findViewById(R.id.bottom_create_image);
                // TODO: 20.07.2023 рисунки на экране
                im1 = itemView.findViewById(R.id.im1);
                im2 = itemView.findViewById(R.id.im2);
                im3 = itemView.findViewById(R.id.im3);
                im4 = itemView.findViewById(R.id.im4);
                // TODO: 07.12.2022 новые
                textipputmaretialttn = itemView.findViewById(R.id.textipputmaretialttn);
                textipputmaretialttdata  = itemView.findViewById(R.id.textipputmaretialttdata);
                textviewmaterialttn  = itemView.findViewById(R.id.textviewmaterialttn);
                textviewmaterialttndata = itemView.findViewById(R.id.textviewmaterialttndata);
                Boolean     ФлагДляСкрытыхМатериалов = preferencesМатериалы.getBoolean("ФлагДляСкрытыхМатериалов",false);
                RelativeLayout.LayoutParams params= null;
                if (textviewnamecfo!=null) {
                    params = (RelativeLayout.LayoutParams)textviewnamecfo.getLayoutParams();
                }
                if (ФлагДляСкрытыхМатериалов==true) {
                    if (textipputmaretialttn!=null) {
                        textipputmaretialttn.setVisibility(View.VISIBLE);
                        textipputmaretialttdata .setVisibility(View.VISIBLE);
                        textviewmaterialttndata.setVisibility(View.VISIBLE);
                        textviewmaterialttn.setVisibility(View.VISIBLE);
                    }
                    if (params!=null) {
                        params.setMargins(0,5,0,0);
                    }
                    if (textviewnamecfo!=null) {
                        textviewnamecfo.setLayoutParams(params);
                    }
                } else {
                    if (textipputmaretialttn!=null) {
                        textipputmaretialttn.setVisibility(View.GONE);
                        textipputmaretialttdata .setVisibility(View.GONE);
                        textviewmaterialttndata.setVisibility(View.GONE);
                        textviewmaterialttn.setVisibility(View.GONE);
                    }
                    if (params!=null) {
                        params.setMargins(0,50,0,0);
                    }
                    if (textviewnamecfo!=null) {
                        textviewnamecfo.setLayoutParams(params);
                    }
                }
                // TODO: 30.06.2023  слушатель
                if (textipputmaretialttdata!=null) {
                    методДатаКликаДляНовогоМатериала(textipputmaretialttdata);
                }
                Log.d(this.getClass().getName(), " cardViewМатериал   " + bottomcreateassionmaterial);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }

    // TODO: 01.07.2023  второй


    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolderIsProcessing extends RecyclerView.ViewHolder {
        // TODO: 28.10.2022
        public MyViewHolderIsProcessing(@NonNull View itemView) {
            super(itemView);
            try {
                    Log.d(this.getClass().getName(), "   itemView   " + itemView);
                Log.d(this.getClass().getName(), "   itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }

    void методДатаКликаДляНовогоМатериала(@NonNull  TextView textipputmaretialttdata){
  try{
      textipputmaretialttdata.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // TODO: 30.06.2023  создание новой даты
            методGetDateForNewOrder(textipputmaretialttdata);
              Log.d(this.getClass().getName(), "\n" + " class " +
                      Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
          }
      });

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


    }










    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LinkedHashMap<String, Object> cursorConcurrentSkipListMap;
        public MyRecycleViewAdapter(@NotNull LinkedHashMap<String, Object> cursorConcurrentSkipListMap) {
            this.cursorConcurrentSkipListMap = cursorConcurrentSkipListMap;
            if ( cursorConcurrentSkipListMap!=null) {
                Log.i(this.getClass().getName(), " cursor  " + cursorConcurrentSkipListMap.size());

            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                //  ХэшДааныеСтрока = (ConcurrentSkipListMap<String, String>) ArrayListДанныеОтСканироваиниеДивайсов.get(position);
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " holder  "+holder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
            return super.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

            recyclerView.removeAllViews();

            recyclerView.getRecycledViewPool().clear();
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
            try {
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewПолучениеМатериалов = null;
            try {
                if(asyncTaskLoader.isStarted()){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov_new, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
                }else {
                    if(  CursorДляЦФО.getCount()>0 ){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_new_assitionmaterial_cardview_new2, parent, false);//todo simple_for_new_assitionmaterial_cardview1_test
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
                } else {
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisl_sprachnikov, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  CursorДляЦФО " + CursorДляЦФО);
                }
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder + "  binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
                if (!asyncTaskLoader.isStarted()) {
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder);
                    МетодАнимации(holder);
                }
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодАнимации(MyViewHolder holder) {
            try {
                  holder.bottomcreateassionmaterial.startAnimation(animationscroll);
                  holder.bottom_create_image.startAnimation(animationscroll);
                holder.textViewcfo.startAnimation(animation);
                holder.marerialtextgroupmaterial.startAnimation(animation);
                holder.materialtext_onematerial_ves.startAnimation(animation);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                    МетодДанныеЦФО(holder);
                    МетодДанныеГруппаМатериалов(holder);
                    МетодДанныеОдинМатериалВесовые(holder);
                    МетоднажатиеСозданиеМатериалов(holder);
                    методСозданиеNewImage(holder);
                    // TODO: 19.12.2022  ДВА НОВЫХ МЕТОДА ттн И ДАТА ТТН
                    МетодТТН(holder);
                    МетодДатаТТН(holder);
                    // TODO: 23.12.2022  новые два поля Автомобиль и Котрагент
                    МетодДанныеАвтомобили(holder);
                    МетодДанныеКонтагент(holder);
                    МетодДанныеНастйрокаВводаКоличества(holder);

                    // TODO: 16.12.2022  указываем флаг что мы один раз прошли по строчкем
                    ФлагЧтоУжепервыйПрогоУжеПрошул=true;
                    Log.i(this.getClass().getName(), "    holder. ФдагЧтоУжеОдинРАзБылПервыйПроход "+  ФлагЧтоУжепервыйПрогоУжеПрошул+
                             " asyncTaskLoader.isStarted() " );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и даты ТТН
        private void МетодДатаТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String УжеВыбраннаяТТН = preferencesМатериалы.getString("НазваниеВыбраногоДатаТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                           holder.      textipputmaretialttdata.setText(УжеВыбраннаяТТН);
                    holder.      textipputmaretialttdata.refreshDrawableState();
                    holder.      textipputmaretialttdata.requestLayout();
                }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и
        private void МетодТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String ВыбранаяДатаТТГУже = preferencesМатериалы.getString("ПозицияВыбраногоТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                    holder. textipputmaretialttn.setText(ВыбранаяДатаТТГУже);
                    holder. textipputmaretialttn.refreshDrawableState();
                    holder. textipputmaretialttn.requestLayout();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 19.10.2022 заполеения ЦФО ФРАГМЕНТ ПОЛУЧЕНИЕ МАТЕРИАЛОВ
        private void МетодДанныеЦФО(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул ==false){
                    Integer ПозицияВыбраногоЦФО=            preferencesМатериалы.getInt("ПозицияВыбраногоЦФО",0);
                    String НазваниеВыбраногоЦФО=            preferencesМатериалы.getString("НазваниеВыбраногоЦФО","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоЦФО);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоЦФО);
                    holder.textViewcfo.setTag(bundle);
                    holder.textViewcfo.setText(НазваниеВыбраногоЦФО);

                    holder.textViewcfo.refreshDrawableState();
                    holder.textViewcfo.requestLayout();
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ ЦФО
                class ЦфоНовыйФильтДляЦФО extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляЦФО);
                    }
                }
                new ЦфоНовыйФильтДляЦФО().МетодЗапускаНовогоФильтра(holder.textViewcfo,holder,
                        "ЦФО","cfo","name");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодПоискаФильтр(@NonNull   SearchView searchViewДляНовогоЦФО,
                                       @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                       @NonNull MyViewHolder holder,
                                       @NonNull View v,
                                       @NonNull String ТаблицаДляФильтра,
                                       @NonNull MaterialTextView materialTextView) {
            try{

                searchViewДляНовогоЦФО.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                        Log.d(this.getClass().getName()," position");
                            if (hasFocus==true) {
                                // searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
                                if(materialTextView.getId()==holder.textViewcfo.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск цфо");
                                }
                                if(materialTextView.getId()==holder.marerialtextgroupmaterial.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск гр. материалов");
                                }
                                if(materialTextView.getId()==holder.materialtext_onematerial_ves.getId()) {
                                    ((SearchView) v).setQueryHint("Поиск материал");
                                }
                            }
                        } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });



                searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                        Log.d(this.getClass().getName()," position");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                        Log.d(this.getClass().getName()," position");
                        Filter filter= simpleCursorAdapterЦФО.getFilter();
                        filter.filter(newText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return true;
                    }
                });
                simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        Log.d(this.getClass().getName()," position");
                        try{
                            // TODO: 30.06.2023  метод полчение данных для поиска
                            методGetCursorForQuertyFilter(constraint);

                            // TODO: 17.04.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " constraint " +constraint);
                            
                            handler.post(()->{
                                simpleCursorAdapterЦФО.swapCursor(holder.cursorДляВсехМатериалов);
                                simpleCursorAdapterЦФО.notifyDataSetChanged();
                             holder.   listViewДляЦФО.setSelection(0);
                                if (holder.cursorДляВсехМатериалов.getCount()==0) {
                                    searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                    handler.postDelayed(() -> {
                                        searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                    }, 250);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return holder.cursorДляВсехМатериалов;
                    }

                   void методGetCursorForQuertyFilter(CharSequence constraint) {
                        try{
                        if(materialTextView.getId()==holder.materialtext_onematerial_ves.getId()) {
                            Bundle bundleДляПосика = (Bundle) holder.marerialtextgroupmaterial.getTag();
                            Integer ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска = bundleДляПосика.getInt("ПолучаемIDЦфо");
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска);
                    }else if (materialTextView.getId()==holder. valueavtomobil.getId()){
                            Log.d(this.getClass().getName(), "   holder. valueavtomobil.getId() " + holder. valueavtomobil.getId());
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);

                    }else if (materialTextView.getId()==holder. valuekontragent.getId()){
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);
                        }else{
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанныеИзПосика(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }

        // TODO: 02.08.2022
        protected   Cursor МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр,Integer ЗначениеГруппыМатериаловДляПосика){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                bundleДляПЕредачи.putInt("ЗначениеГруппыМатериаловДляПосика",ЗначениеГруппыМатериаловДляПосика);
                Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }


        // TODO: 19.10.2022 ГРУППА МАТЕРИАЛОВ
        private void МетодДанныеГруппаМатериалов(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоМатериалов=            preferencesМатериалы.getInt("ПозицияВыбраногоГруппыМатериалов",0);
                    String НазваниеВыбраногоМатериалов=            preferencesМатериалы.getString("НазваниеВыбраногоГруппыМатериалов","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоМатериалов);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоМатериалов);
                    holder.marerialtextgroupmaterial.setTag(bundle);
                    holder.marerialtextgroupmaterial.setText(НазваниеВыбраногоМатериалов);

                    holder.marerialtextgroupmaterial.refreshDrawableState();

                    holder.marerialtextgroupmaterial.requestLayout();
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ группы МАТЕРИАЛОВ
                class ДляНовгоФимльтраГруппыМатериалов extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляГруппаМатериалов);
                    }
                }
                new ДляНовгоФимльтраГруппыМатериалов().МетодЗапускаНовогоФильтра(holder.marerialtextgroupmaterial, holder,"Группа материалов",
                        "type_materials","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ ОДИН МАТЕРИАЛ ПОСЛЕ ВСЕХ ВЕСОВЫЕ
        private void МетодДанныеОдинМатериалВесовые(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Одного МАТЕРАИАЛ ОДДИН БЫШИЙ ВЕСОВОЙ
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоГруппыМатериалов=            preferencesМатериалы.getInt("ПозицияВыбраногоМатериала",0);
                    String НазваниеВыбраногоГруппыМатериалов=            preferencesМатериалы.getString("НазваниеВыбраногоМатериала","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоГруппыМатериалов);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоГруппыМатериалов);
                    holder.materialtext_onematerial_ves.setTag(bundle);
                    holder.materialtext_onematerial_ves.setText(НазваниеВыбраногоГруппыМатериалов);

                    holder.materialtext_onematerial_ves.refreshDrawableState();

                    holder.materialtext_onematerial_ves.requestLayout();
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ ЦФО
                class ОдинМатериаДляФильтраВышшаяВесовая extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляОдногоМатериалаБышВесов);
                    }
                }
                new ОдинМатериаДляФильтраВышшаяВесовая().МетодЗапускаНовогоФильтра(holder.materialtext_onematerial_ves,holder,"материал",
                        "nomen_vesov","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Автомобиля
        private void МетодДанныеАвтомобили(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомобили
                Boolean ФлагВыбиралУжеАвтомобилиИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеАвтомобилиИзСпинера==true && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоАвтомобили=            preferencesМатериалы.getInt("ПозицияВыбраногоАвтомобили",0);
                    String НазваниеВыбраногоАтомобиля=            preferencesМатериалы.getString("НазваниеВыбраногоАвтомобили","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоАвтомобили);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоАтомобиля);
                    holder.valueavtomobil.setTag(bundle);
                    holder.valueavtomobil.setText(НазваниеВыбраногоАтомобиля);

                    holder.valueavtomobil.refreshDrawableState();

                    holder.valueavtomobil.requestLayout();
                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомомиби
                class НовыйФильДляАвтомобили extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляАвтомобиля);
                    }
                }
                new НовыйФильДляАвтомобили().МетодЗапускаНовогоФильтра(holder.valueavtomobil,holder,"автомобиль","track","fullname");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Контагент
        private void МетодДанныеКонтагент(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Контрагенты
                Boolean ФлагВыбиралУжеКонтагенты=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеКонтагенты==true && ФлагЧтоУжепервыйПрогоУжеПрошул==false ){
                    Integer ПозицияВыбраногоКонтрагент=            preferencesМатериалы.getInt("ПозицияВыбраногоКонтрагент",0);
                    String НазваниеВыбраногоКонтрагент=            preferencesМатериалы.getString("НазваниеВыбраногоКонтрагент","");
                    Bundle bundle=new Bundle();
                    bundle.putInt("ПолучаемIDЦфо",ПозицияВыбраногоКонтрагент);
                    bundle.putString("НазваниеЦФО",НазваниеВыбраногоКонтрагент);
                    holder.valuekontragent.setTag(bundle);
                    holder.valuekontragent.setText(НазваниеВыбраногоКонтрагент);


                    holder.valuekontragent.refreshDrawableState();

                    holder.valuekontragent.requestLayout();

                }
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Автомомиби
                class НовыйФильДляКонтрагенты extends SubClassNewFilterSFOНовыйФильтДанных{
                    @Override
                    protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        super.МетодКоторыйОперделянтТекуийКурсор(holder, CursorДляКонтрагента);
                    }
                }
                new НовыйФильДляКонтрагенты().МетодЗапускаНовогоФильтра(holder.valuekontragent,holder,
                        "контрагент","company","name");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 19.10.2022 ПОЛУЧАЕМ Контагент
        private void МетодДанныеНастйрокаВводаКоличества(@NonNull MyViewHolder holder) {
            try {

                // TODO: 23.01.2023
                KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        // TODO: 23.01.2023
                   if(isOpen==true){

                       scrollViewНовыйматериал.scrollBy(0, +500);
                    scrollViewНовыйматериал.fullScroll(View.FOCUS_DOWN);
                       scrollViewНовыйматериал.smoothScrollTo(0, 500);
                    scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom()-1);

                   }else{
                       scrollViewНовыйматериал.scrollBy(0, 0);
                       scrollViewНовыйматериал.fullScroll(View.FOCUS_UP);
                       scrollViewНовыйматериал.smoothScrollTo(0, 0);
                       scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom());
                   }
                        scrollViewНовыйматериал.refreshDrawableState();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 20.10.2022 нажатие на кнопку
        private  void МетоднажатиеСозданиеМатериалов(@NonNull MyViewHolder holder){
            try{
                holder. bottomcreateassionmaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(this.getClass().getName(), " МетоднажатиеСозданиеМатериалов  ");
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
                        handler.postDelayed(()->{
                            Intent intentСамоПолучениеНовогоМатериала=new Intent("СамоСозданиеНовогоМатериала");
                            // TODO: 21.10.2022  перед созданием нового материала получанеим Данные которые нужно вставить
                            Integer IDДляВставкиЦФО= 0;
                            if (   holder.textViewcfo.getText().length()>0) {
                                Bundle bundle=(Bundle)   holder.textViewcfo.getTag();
                                IDДляВставкиЦФО=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиГруппыматериалов= 0;
                            if (   holder.marerialtextgroupmaterial.length()>0) {
                                Bundle bundle=(Bundle)   holder.marerialtextgroupmaterial.getTag();
                                IDДляВставкиГруппыматериалов=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиОдногоматериала= 0;
                            if (    holder.materialtext_onematerial_ves.length()>0) {
                                Bundle bundle=(Bundle)   holder.materialtext_onematerial_ves.getTag();
                                IDДляВставкиОдногоматериала=      bundle.getInt("ПолучаемIDЦфо",0);
                            }

                            Integer ВыбраноеЗначениеКоличествоМатериала= 0;
                            if (holder.textipputcountassinmaterail.getText().length()>0) {
                                ВыбраноеЗначениеКоличествоМатериала =Integer.parseInt(holder.textipputcountassinmaterail.getText().toString());
                            }
                            String ВыбраноеТТНМатериала=new String();
                            if (holder.textipputmaretialttn.getText().length()>0) {
                                ВыбраноеТТНМатериала = holder.textipputmaretialttn.getText().toString();
                            }
                            String ВыбраноеТТНДата=new String();
                            if (holder.textipputmaretialttdata.getText().length()>0) {
                                ВыбраноеТТНДата = holder.textipputmaretialttdata.getText().toString();
                            }
                            // TODO: 27.12.2022 ДВА НОВЫХ ПОЛЯ АВТОМОБИЛИ И ИЛИ  КОНТРАГЕНТЫ
                            Integer ВыбраноеАвтомобили=0;
                            if (holder.valueavtomobil.getText().length()>0) {
                                Bundle bundleАвтомобили=  (Bundle) holder.valueavtomobil.getTag();
                                ВыбраноеАвтомобили=  bundleАвтомобили.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеАвтомобили " +ВыбраноеАвтомобили);

                            }
                            Integer ВыбраноеКонтагенты=0;
                            if (holder.valuekontragent.getText().length()>0) {
                                Bundle bundleКонтрагенты=  (Bundle) holder.valuekontragent.getTag();
                                ВыбраноеКонтагенты=  bundleКонтрагенты.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            }
                            Log.d(this.getClass().getName(), " IDДляВставкиМатериалы " +IDДляВставкиГруппыматериалов +
                                    " IDДляВставкиВесовые "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО+
                                    " ВыбраноеЗначениеКоличествоМатериала " +ВыбраноеЗначениеКоличествоМатериала+ " ВыбраноеТТНМатериала " +ВыбраноеТТНМатериала+
                                    " ВыбраноеАвтомобили " +ВыбраноеАвтомобили + " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            // TODO: 21.10.2022 проверка выбрали ли все ТРИ СПРАВОЧНИКА ДЛЯ СОЗДАНИЯ НОВОГО МАТЕРИАЛА
                            if(IDДляВставкиГруппыматериалов>0 && IDДляВставкиОдногоматериала>0
                                    &&  IDДляВставкиЦФО>0 && ВыбраноеЗначениеКоличествоМатериала>0f && ВыбраноеКонтагенты>0 ){
                                Bundle bundleДляСоздании=new Bundle();
                                bundleДляСоздании.putInt("cfo",IDДляВставкиЦФО);
                                bundleДляСоздании.putInt("type_material",IDДляВставкиГруппыматериалов);
                                bundleДляСоздании.putInt("nomen_vesov",IDДляВставкиОдногоматериала);
                                bundleДляСоздании.putInt("count",ВыбраноеЗначениеКоличествоМатериала);
                                // TODO: 27.12.2022 для двух новых полей автомиби и контрагент
                                bundleДляСоздании.putString("ttn",ВыбраноеТТНМатериала);
                                bundleДляСоздании.putString("datattn",ВыбраноеТТНДата);
                                // TODO: 27.12.2022  два новых поля для создание материла и контрагент
                                bundleДляСоздании.putInt("tracks",ВыбраноеАвтомобили);
                                bundleДляСоздании.putInt("companys",ВыбраноеКонтагенты);
                                intentСамоПолучениеНовогоМатериала.putExtras(bundleДляСоздании);
                                Integer ХэшРезультататСозданияСозданиеНовогоМатериала =
                                        binderДляПолучениеМатериалов.getService().МетодCлужбыСозданиеНовогоМатериала(getContext(),intentСамоПолучениеНовогоМатериала);
                                // TODO: 21.10.2022  результат  создание нового материала
                                Log.d(this.getClass().getName(), " ХэшРезультататСозданияСозданиеНовогоМатериала  "+ХэшРезультататСозданияСозданиеНовогоМатериала+
                                        " ХэшРезультататСозданияСозданиеНовогоМатериала " +ХэшРезультататСозданияСозданиеНовогоМатериала);


                                if (ХэшРезультататСозданияСозданиеНовогоМатериала>0) {
                                    методBackToFragmentAdmissionMaterilas(v);
                                    // TODO: 17.11.2022 запоманаем выбраное цфо
                                    SharedPreferences.Editor editor = preferencesМатериалы.edit();
                                    editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
                                    // TODO: 09.12.2022 цфо
                                    editor.putInt("ПозицияВыбраногоЦФО",IDДляВставкиЦФО);
                                    editor.putString("НазваниеВыбраногоЦФО",holder.textViewcfo.getText().toString());
                                    // TODO: 09.12.2022 группа материалов
                                    editor.putInt("ПозицияВыбраногоГруппыМатериалов",IDДляВставкиГруппыматериалов);
                                    editor.putString("НазваниеВыбраногоГруппыМатериалов",holder.marerialtextgroupmaterial.getText().toString());
                                    // TODO: 09.12.2022 сам матералов
                                    editor.putInt("ПозицияВыбраногоМатериала",IDДляВставкиОдногоматериала);
                                    editor.putString("НазваниеВыбраногоМатериала",holder.materialtext_onematerial_ves.getText().toString());
                                    // TODO: 09.12.2022 два поля ТТН и ТТН ДАТА
                                    Boolean     ФлагДляСкрытыхМатериалов = preferencesМатериалы.getBoolean("ФлагДляСкрытыхМатериалов",false);
                                    if (ФлагДляСкрытыхМатериалов==true) {
                                        editor.putString("ПозицияВыбраногоТТН",holder.textipputmaretialttn.getText().toString());
                                        editor.putString("НазваниеВыбраногоДатаТТН",holder.textipputmaretialttdata.getText().toString());
                                    }
                                    // TODO: 27.12.2022  ДВА НОВЫХ ПОЛЯ МАТЕРИАЛОВ КОНТРАГЕНТ И АВТОМОБИЛЬ
                                    // TODO: 09.12.2022 Автомобиль запоминаем
                                    editor.putInt("ПозицияВыбраногоАвтомобили",ВыбраноеАвтомобили);
                                    editor.putString("НазваниеВыбраногоАвтомобили",holder.valueavtomobil.getText().toString());
                                    // TODO: 09.12.2022 Автомобиль Контрогент
                                    editor.putInt("ПозицияВыбраногоКонтрагент",ВыбраноеКонтагенты);
                                    editor.putString("НазваниеВыбраногоКонтрагент",holder.valuekontragent .getText().toString());
                                    // TODO: 27.12.2022 запоминаем параметры
                                    editor.commit();


                                    // TODO: 30.06.2023  Запуск Синхрониазции
                                    методЗарускОдноразовойСнхрониазцииПослеСозданиеНовгоЗаказа();
                                    
                                    
                                }else {
                                    Snackbar.make(v, "Материалал не добавился !!!" +
                                            " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                                }
                            }else {
                                Log.d(this.getClass().getName(), " IDДляВставкиГруппыматериалов " +IDДляВставкиГруппыматериалов +
                                        " IDДляВставкиОдногоматериала "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО);
                                Snackbar.make(v, "Выберете все три справочника и/или количество" +
                                        " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                            }
                            Log.d(this.getClass().getName(), "  v  " + v);
                        },150);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
// TODO: 20.07.2023 КНОПКА добавления нового Image
private  void методСозданиеNewImage(@NonNull MyViewHolder holder){
    try{
        holder. bottom_create_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().rotationX(-40l);
                handler .postDelayed(()->{
                    v.animate().rotationX(0);
                    // TODO: 20.07.2023 создане нового рисунка
                    SubClassCreateNewImageForMateril subClassCreateNewImageForMateril=new SubClassCreateNewImageForMateril();

                    subClassCreateNewImageForMateril.new SubClassUploadImageAnFragmentImage().методЗагрузкиИлиСозданиеImage(holder);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                },200);

            }
        });
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
}

        @Override
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            int КоличесвоСтрок = 0;
            try {
                КоличесвоСтрок = 1;
                Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return КоличесвоСтрок;
        }



        // TODO: 15.12.2022 класс для всех новый фильр дл я данных
        public class SubClassNewFilterSFOНовыйФильтДанных {
       private       MaterialTextView materialTextView;
            private    Cursor cursor;
            private    MyViewHolder holder;
            private    Boolean ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=false;
            protected SubClassNewFilterSFOНовыйФильтДанных(){
                Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО ");
            }
            protected   void МетодЗапускаНовогоФильтра( @NonNull MaterialTextView materialTextView,
                                                         @NonNull MyViewHolder holder,
                                                         @NonNull String ФлагРежимНовогоФильтра,
                                                         @NonNull String ТаблицаДляФильтра,
                                                        @NonNull String КакойСтолбикЗагружатьВSimpleAdapter) {
                this.materialTextView = materialTextView;
                this.cursor = cursor;
                this.holder = holder;
                // TODO: 16.12.2022
                materialTextView.setOnClickListener(new View.OnClickListener() {
                    MaterialButton    materialButtonЗакрытьДиалогSearveView=null;
                    @Override
                    public void onClick(View v) {
                        try{

                        Animation   animation1= AnimationUtils.loadAnimation(v.getContext(),R.anim.slide_in_row_newscanner1);
                        // TODO: 20.12.2022  оперделяем какой вид загружать когда выбран и когда не выбрана гурппа метарилов
                        Integer КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview;
                        if(materialTextView.getId()== holder.materialtext_onematerial_ves.getId()){
                            if ( holder.marerialtextgroupmaterial.getText().length()==0) {
                                КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview_kogda_net_group;
                            //   Snackbar.make(v, "Вы не выбрали группу материалов !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                                ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=true;
                            }else{
                                ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов=false;
                            }
                        }
                        // TODO: 28.12.2022 cursor
                        holder.alertDialog  = new MaterialAlertDialogBuilder(v.getContext()){
                            @NonNull
                            @Override
                            public MaterialAlertDialogBuilder setView(View view) {
                                try{
                                    // TODO: 14.12.2022
                                   materialButtonЗакрытьДиалогSearveView =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                                // TODO: 19.12.2022 какой КУРСОР БУДЕТ ВЫПОЛНЯТЬСЯ
                                if (ФлагКогдаМыВыбралиОдинМатреиалИНАНегоНетГруппаМатериалов==false) {
                                    SearchView searchViewДляНовогоЦФО= null;

                                        МетодКоторыйОперделянтТекуийКурсор(holder, cursor);

                                    Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО " + cursor);
                                    holder.  listViewДляЦФО =    (ListView) view.findViewById(R.id.SearchViewList);
                                    if (holder.  listViewДляЦФО!=null) {
                                    holder.   listViewДляЦФО.setTextFilterEnabled(true);
                                    searchViewДляНовогоЦФО = (SearchView) view.findViewById(R.id.searchview_newscanner);
                                    // TODO: 14.12.2022
                                    searchViewДляНовогоЦФО.setDrawingCacheBackgroundColor(Color.GRAY);
                                    searchViewДляНовогоЦФО.setDrawingCacheEnabled(true);
                                    int id = searchViewДляНовогоЦФО.getContext()
                                            .getResources()
                                            .getIdentifier("android:id/search_src_text", null, null);
                                    TextView textView = (TextView) searchViewДляНовогоЦФО.findViewById(id);
                                    textView.setTextColor(Color.rgb(0,102,102));
                                    textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                }



                                        if(materialTextView.getId()== holder.materialtext_onematerial_ves.getId()) {
                                            if(      holder.cursorДляВсехМатериалов.getCount()==0) {
                                                holder.cursorДляВсехМатериалов = методПолучениеДанныхЕслиУжеЗаполеныПоляВсахроненииВНАстройкахтелефона(holder);
                                                // TODO: 17.04.2023
                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " holder.cursorДляВсехМатериалов " + holder.cursorДляВсехМатериалов);
                                            }
                                        }

                                    // TODO: 17.04.2023
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " holder.cursorДляВсехМатериалов " +holder.cursorДляВсехМатериалов);


                                    ///TODO ГЛАВНЫЙ АДАПТЕР чата
                                    SimpleCursorAdapter    simpleCursorAdapterЦФО= new SimpleCursorAdapter(v.getContext(),  R.layout.simple_newspinner_dwonload_newfiltersearch,// R.layout.simple_newspinner_dwonload_cfo2,
                                            holder.cursorДляВсехМатериалов,
                                            new String[]{ КакойСтолбикЗагружатьВSimpleAdapter,"_id"},
                                            new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                                    SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder(){

                                        @Override
                                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                            switch (view.getId()) {
                                                case android.R.id.text1:
                                                    Log.d(this.getClass().getName()," position");
                                                    if (cursor.getCount()>0) {
                                                        try{
                                                            MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                            Integer ИндексНазваниеЦФО = cursor.getColumnIndex(КакойСтолбикЗагружатьВSimpleAdapter);///user_update  --old/// uuid
                                                            String  НазваниеЦФО = cursor.getString(ИндексНазваниеЦФО);
                                                            // TODO: 13.12.2022  производим состыковку
                                                            Integer   ИндексНазваниеЦфоID = cursor.getColumnIndex("_id");///user_update  --old/// uuid
                                                            Integer   ПолучаемIDЦфо = cursor.getInt(ИндексНазваниеЦфоID);
                                                            if (ПолучаемIDЦфо>0) {
                                                                Integer UUIDНазваниеЦФО = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                                Long UUIDЦФО = cursor.getLong(UUIDНазваниеЦФО);
                                                                Bundle bundle=new Bundle();
                                                                bundle.putInt("ПолучаемIDЦфо",ПолучаемIDЦфо);
                                                                bundle.putString("НазваниеЦФО",НазваниеЦФО);
                                                                bundle.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
                                                                materialTextViewЭлементСписка.setTag(bundle);
                                                            }
                                                            Log.d(this.getClass().getName()," НазваниеЦФО"+ НазваниеЦФО+
                                                                    " ПолучаемIDЦфо "+ПолучаемIDЦфо);
                                                            // TODO: 20.01.2022
                                                            Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеЦФО);
                                                            boolean ДлинаСтрокивСпиноре = НазваниеЦФО.length() >40;
                                                            if (ДлинаСтрокивСпиноре==true) {
                                                                StringBuffer sb = new StringBuffer(НазваниеЦФО);
                                                                sb.insert(40, System.lineSeparator());
                                                                НазваниеЦФО = sb.toString();
                                                                Log.d(v.getContext().getClass().getName(), " НазваниеЦФО " + "--" + НазваниеЦФО);/////
                                                            }
                                                            ((MaterialTextView)view).setText(НазваниеЦФО);

                                                            materialTextViewЭлементСписка.startAnimation(animation);
                                                            // TODO: 13.12.2022 слушатель

                                                            // TODO: 13.12.2022 филь
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                                    this.getClass().getName(),
                                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        }
                                                        return true;
                                                    } else {
                                                        Log.d(this.getClass().getName()," position");
                                                        return false;
                                                    }
                                            }
                                            return false;
                                        }
                                    };
                                    simpleCursorAdapterЦФО.setViewBinder(БиндингДляЦФО);
                                    simpleCursorAdapterЦФО.notifyDataSetChanged();
                                    holder.listViewДляЦФО.setAdapter(simpleCursorAdapterЦФО);
                                    holder.listViewДляЦФО.startAnimation(animation);
                                    holder.listViewДляЦФО.setSelection(0);
                                    holder.listViewДляЦФО.refreshDrawableState();
                                    holder.listViewДляЦФО.requestLayout();

                                    // TODO: 13.12.2022  Поиск и его слушель
                                    МетодПоискаФильтр(searchViewДляНовогоЦФО,simpleCursorAdapterЦФО,holder,v,ТаблицаДляФильтра,materialTextView);

                                    МетодЗакрытиеНовогоПосика(materialButtonЗакрытьДиалогSearveView);


                                    методКликПоДанным();


                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "materialButtonЗакрытьДиалогSearveView"+  materialButtonЗакрытьДиалогSearveView);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                                return super.setView(view);
                                // TODO: 20.12.2022  тут конец выбеленого
                            }

                            private void методКликПоДанным() {
                                holder.listViewДляЦФО.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                try{
                                                    MaterialTextView materialTextViewЭлементСписка=    (MaterialTextView)       parent.getAdapter().getView(position, view,parent );

                                                    if (materialTextViewЭлементСписка!=null) {
                                                        //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                        materialTextViewЭлементСписка.startAnimation(animation);
                                                        Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                        Integer IDЦфоДЛяПередачи=      bundle.getInt("ПолучаемIDЦфо",0);
                                                        String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
                                                        Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
                                                        materialTextView.setTag(bundle);
                                                        materialTextView.setText(НазваниеЦФО);
                                                        materialTextView.refreshDrawableState();
                                                        materialTextView.forceLayout();

                                                        if (    materialTextView.getText().toString().length()==0) {
                                                            Snackbar.make(view, " Вы не выбрали цфо !!! "
                                                                    , Snackbar.LENGTH_LONG).show();
                                                            materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                            materialTextView.setTextColor(Color.GRAY);
                                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                        } else {
                                                            materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                            materialTextView.setTextColor(Color.BLACK);
                                                            holder.alertDialog.dismiss();
                                                            holder. alertDialog.cancel();
                                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());


                                                            // TODO: 16.12.2022  для Второго Компонета ГРУППА МАТЕРИАЛОВ, после ВЫБЫБОРА ГУРППЫ МАТЕРИАЛОВ ДАЛЕЕ ИНИЦИАЛИЗУЕМ ОДИН МАТЕРИАЛОВ
                                                            МетодДляГруппыМатериаловЗапускатьОдинМатериаловВесовой(bundle,holder);

                                                        }
                                                        Log.d(this.getClass().getName()," position");
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                            this.getClass().getName(),
                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                            }

                                });
                            }

                            void методGetCursorForQuertyFilter(CharSequence constraint) {
                                try{
                                    if(materialTextView.getId()==holder.materialtext_onematerial_ves.getId()) {
                                        Bundle bundleДляПосика = (Bundle) holder.marerialtextgroupmaterial.getTag();
                                        Integer ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска = bundleДляПосика.getInt("ПолучаемIDЦфо");
                                        holder.cursorДляВсехМатериалов = (Cursor)
                                                МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),
                                                        ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска);
                                    }else if (materialTextView.getId()==holder. valueavtomobil.getId()){
                                        Log.d(this.getClass().getName(), "   holder. valueavtomobil.getId() " + holder. valueavtomobil.getId());
                                        holder.cursorДляВсехМатериалов = (Cursor)
                                                МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);

                                    }else if (materialTextView.getId()==holder. valuekontragent.getId()){
                                        holder.cursorДляВсехМатериалов = (Cursor)
                                                МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);
                                    }else{
                                        holder.cursorДляВсехМатериалов = (Cursor)
                                                МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),0);
                                    }
                                    // TODO: 17.04.2023
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }


                        }
                                .setTitle(ФлагРежимНовогоФильтра)
                                .setCancelable(false)
                                .setIcon( R.drawable.icon_newscannertwo)
                                .setView(getLayoutInflater().inflate( КакойИмменоВидЗагружатьДляНовогоПосика, null )).show();
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.copyFrom(   holder.alertDialog.getWindow().getAttributes());
                        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.gravity = Gravity.CENTER;
                        holder.alertDialog.getWindow().setAttributes(layoutParams);
                        // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                "materialButtonЗакрытьДиалогSearveView"+  materialButtonЗакрытьДиалогSearveView);

                        Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }

                    private void МетодЗакрытиеНовогоПосика(@NonNull  MaterialButton materialButtonЗакрытьДиалог) {
                        materialButtonЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    Log.d(this.getClass().getName()," position");
                                    Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                                    holder.alertDialog.dismiss();
                                    holder.alertDialog.cancel();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }
                });
            }
            // TODO: 19.12.2022  курсор текущий операйции  какой Спинер
            protected void МетодКоторыйОперделянтТекуийКурсор(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                try{
                holder.cursorДляВсехМатериалов = (Cursor)  cursor;
                    Log.d(this.getClass().getName(),"    holder.cursorДляВсехМатериалов"+   holder.cursorДляВсехМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }

            // TODO: 19.12.2022  курсор текущий операйции  какой Спинер
            protected Cursor методПолучениеДанныхЕслиУжеЗаполеныПоляВсахроненииВНАстройкахтелефона(@NonNull MyViewHolder holder) {
                Cursor cursor1ОдногоМатериала=null;
                try{
                    Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                        Bundle bundle= (Bundle) holder.marerialtextgroupmaterial.getTag();
                        Integer ПолученеиеID=bundle.getInt("ПолучаемIDЦфо");
                  cursor1ОдногоМатериала=       МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,ПолученеиеID);
                        Log.d(this.getClass().getName(),"    holder.cursorДляВсехМатериалов"+   holder.cursorДляВсехМатериалов
                                +  "holder.marerialtextgroupmaterial.getTag() "+holder.marerialtextgroupmaterial.getTag()
                                + "  binderДляПолучениеМатериалов.getService() " +binderДляПолучениеМатериалов.getService() + " cursor1ОдногоМатериала  " +cursor1ОдногоМатериала);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                return  cursor1ОдногоМатериала;
            }

            // TODO: 16.12.2022  для Второго Компонета ГРУППА МАТЕРИАЛОВ, после ВЫБЫБОРА ГУРППЫ МАТЕРИАЛОВ ДАЛЕЕ ИНИЦИАЛИЗУЕМ ОДИН МАТЕРИАЛОВ
            protected void МетодДляГруппыМатериаловЗапускатьОдинМатериаловВесовой(Bundle bundle,@NonNull MyViewHolder holder) {
                try{
                Log.d(this.getClass().getName()," materialTextView.getId() "+materialTextView.getId()+
                        " holder.marerialtextgroupmaterial.getId()   "+ holder.marerialtextgroupmaterial.getId()  );
                if(materialTextView.getId()== holder.marerialtextgroupmaterial.getId() ){
                    Log.d(this.getClass().getName()," materialTextView.getId() "+materialTextView.getId()+
                            " holder.marerialtextgroupmaterial.getId()   "+ holder.marerialtextgroupmaterial.getId()+
                            "  bundle.getInt(\"ПолучаемIDЦфо\",0) " + bundle.getInt("ПолучаемIDЦфо",0));
                    // TODO: 24.10.2022 дополнительное получение весовых через фильтр Группы материалов
                    if (bundle.getInt("ПолучаемIDЦфо",0)>0) {
                            МетодПереопределенияСпинераВесовой(bundle.getInt("ПолучаемIDЦфо",0),holder);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }
            // TODO: 16.12.2022  третий вариант для ВЫбора Материала
            protected void МетодПереопределенияСпинераВесовой(@NonNull  Integer ВытаскиваемЗначениеIDдляФильтра, @NonNull MyViewHolder holder) {
                try{
                    Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                    МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,ВытаскиваемЗначениеIDдляФильтра);
                    Log.d(this.getClass().getName(), " cursor  " + cursor);
                    // TODO: 24.10.2022 перезапускаем Метод Спинера
                    holder.materialtext_onematerial_ves.setText("");
                    holder.materialtext_onematerial_ves.forceLayout();
                 ///   МетодДанныеОдинМатериалВесовые(holder);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
        }
        // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
    }










    private void МетодКпопкиЗначков()
            throws ExecutionException, InterruptedException {
        try {
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.requestLayout();
            bottomNavigationView.forceLayout();
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            recyclerView.refreshDrawableState();
            bottomNavigationView.refreshDrawableState();
            linearLayou.refreshDrawableState();
            linearLayou.requestLayout();
            linearLayou.forceLayout();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 02.08.2022
    void методЗарускОдноразовойСнхрониазцииПослеСозданиеНовгоЗаказа( ){
            try{
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляФрагмента);
                Intent  intentЗапускОднорworkanager=new Intent();
                intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
                Class_Generator_One_WORK_MANAGER class_generator_one_work_manager=        new Class_Generator_One_WORK_MANAGER(getContext());
                // TODO: 02.08.2022
                class_generator_one_work_manager.МетодОдноразовыйЗапускВоерМенеджера(getContext(),intentЗапускОднорworkanager);
                // TODO: 26.06.2022
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ПубличныйIDДляОдноразовойСинхронПубличныйIDДляФрагментаиазции "+ПубличныйIDДляФрагмента );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

                // TODO: 11.05.2021 запись ошибок
            }
    }
    // TODO: 19.10.2022  Класс ДАты

        void  методGetDateForNewOrder( @NonNull      TextView textipputmaretialttdata){
            try{
                final String[] FullNameCFO = new String[1];
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                Calendar newDate = Calendar.getInstance();
                //TODODATA
                if (ДатаДляКалендаря==null) {
                    ДатаДляКалендаря =
                            new DatePickerDialog(getContext(), android.R.style.Theme_Holo_InputMethod , new DatePickerDialog.OnDateSetListener() {////Theme_Holo_Dialog_MinWidth  //Theme_Holo_Panel
                                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    newDate.set(year, monthOfYear, dayOfMonth);
                                    try {
                                        //  String ДатаДляНовогоЗаказаТраспорта= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(newDate.getTime());
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy" , new Locale("ru"));
                                        SimpleDateFormat simpleDateFormatBungle = new SimpleDateFormat("yyyy-MM-dd" , new Locale("ru"));
                                        Date ДатаВыбранаяПользователь= newDate.getTime();
                                        // TODO: 09.06.2023
                                        Calendar myCal = new GregorianCalendar();
                                        Date ДатаСейчасСитемная= myCal.getTime();
                                        // TODO: 09.06.2023  проверяем что выбрана дата старше чем сейчас
                                        if (        ДатаВыбранаяПользователь.after(ДатаСейчасСитемная)) {
                                            String    ДатаДляНовогоЗаказаТраспорта = simpleDateFormat.format(ДатаВыбранаяПользователь);
                                            String    ДатаДляНовогоЗаказаТраспортаBungle = simpleDateFormatBungle.format(ДатаВыбранаяПользователь);
                                            // TODO: 16.05.2023 дата
                                            методЗаписиНовуюДату(ДатаДляНовогоЗаказаТраспорта, textipputmaretialttdata,ДатаДляНовогоЗаказаТраспортаBungle);
                                            Log.d(getContext() .getClass().getName(), "\n"
                                                    + " время: " + new Date()+"\n+" +
                                                    " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" ДатаДляНовогоЗаказаТраспорта " +ДатаДляНовогоЗаказаТраспорта);
                                        }

                                        Log.d(getContext() .getClass().getName(), "\n"
                                                + " время: " + new Date()+"\n+" +
                                                " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }

                            }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH) +1);
                }
                ДатаДляКалендаря.setTitle("Календарь");
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_POSITIVE, "Создать", ДатаДляКалендаря);
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Закрыть", ДатаДляКалендаря);
                if (!ДатаДляКалендаря.isShowing()) {
                    ДатаДляКалендаря .show();
                }
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
                ////

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        private void методЗаписиНовуюДату(String ДатаДляНовогоЗаказаТраспорта,
                                          @NonNull TextView textipputmaretialttdata,
                                          @NonNull  String ДатаДляНовогоЗаказаТраспортаBungle) {
            try{
                Bundle bundle=new Bundle();
                bundle.putString("GetDateOrder",ДатаДляНовогоЗаказаТраспортаBungle.trim());
                textipputmaretialttdata.setTag(bundle);
                textipputmaretialttdata.setText(ДатаДляНовогоЗаказаТраспорта.toString());
                textipputmaretialttdata.refreshDrawableState();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }














    // TODO: 20.07.2023 класс создание нового создане нового Image
    public class SubClassCreateNewImageForMateril{
        // TODO: 19.07.2023  первый класс создание нового изображения
        public class SubClassUploadImageAnFragmentImage {
            AlertDialog alertDialogCreateImage;
            protected   void методЗагрузкиИлиСозданиеImage(@NonNull MyViewHolder holder) {
                // TODO: 16.12.2022
                        try{
                            Animation   animation1= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_newscanner1);
                            // TODO: 20.12.2022  оперделяем какой вид загружать когда выбран и когда не выбрана гурппа метарилов
                             alertDialogCreateImage  = new MaterialAlertDialogBuilder(getContext()){
                                @NonNull
                                @Override
                                public MaterialAlertDialogBuilder setView(View view) {
                                    try{
                                        // TODO: 14.12.2022
                                        GridView gridViewImage =    (GridView) view.findViewById(R.id.listView_create_image);
                                        MaterialButton  materialButtoтtЗакрываемСозданиеImage =    (MaterialButton) view.findViewById(R.id.bottom_close_create_image);
                                        ArrayList<HashMap<String, Object>> ЛистДляСозданиеРисунки= new ArrayList<HashMap<String, Object>> ();
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("alldonttbels", "Не создано !!!");
                                        map.put("allimage", " dont");
                                        ЛистДляСозданиеРисунки.add(map);
                                        SimpleAdapter АдаптерКогдаНетданных = new SimpleAdapter(getContext(),
                                                ЛистДляСозданиеРисунки,
                                                R.layout.list_item_all_for_create_image_for_material,
                                                new String[]{"alldonttbels","allimage"},
                                                new int[]{android.R.id.text2,android.R.id.text1});

                                        SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                                            @Override
                                            public boolean setViewValue(View view, Object data, String textRepresentation) {
                                                try{


                                                    switch (view.getId()) {
                                                        case android.R.id.text1:
                                                            LinearLayout    linearLayoutGroupBYImageТранспорта = (LinearLayout) view.findViewById(android.R.id.text1);

                                                            // TODO: 23.07.2023  Создание НОВОЙ ФОТОГРАФИИ 
                                                            AppCompatImageButton appCompatImageButtonUpImage=  
                                                                    linearLayoutGroupBYImageТранспорта.findViewById(R.id.appcompatimagebutton_up);
                                                                    методКликПоДаннымUpImage(appCompatImageButtonUpImage );

                                                         // TODO: 23.07.2023  Создание НОВОЙ  SIMPLE ФОТОГРАФИИ 

                                                            AppCompatImageButton appcompatimagebutton_simple_create=
                                                                    linearLayoutGroupBYImageТранспорта.findViewById(R.id.appcompatimagebutton_simple_create);
                                                            методКликПоДаннымUpImage(appcompatimagebutton_simple_create );


                                                            // TODO: 23.07.2023  загружаем уже готовую фотографию , созданое зарание 
                                                            AppCompatImageButton appCompatImageButtonCreateImage=  
                                                                    linearLayoutGroupBYImageТранспорта.findViewById( R.id.appcompatimagebutton_create);
                                                            // TODO: 23.07.2023  метод по клику  
                                                            методКликПоДаннымCreateImage(appCompatImageButtonCreateImage );



                                                            // TODO: 20.07.2023 слушатели  закрытие
                                                            методЗакрываемСозданиеИлиUpIamge(materialButtoтtЗакрываемСозданиеImage, alertDialogCreateImage);




                                                            // TODO: 20.07.2023 методы после создание выбора
                                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
                                                            return  true;
                                                    }
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " );
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                                return false;
                                            }
                                        };
                                        АдаптерКогдаНетданных.setViewBinder(БиндингКогдаНетДАнных);
                                        АдаптерКогдаНетданных.notifyDataSetChanged();
                                        gridViewImage.setAdapter(АдаптерКогдаНетданных);
                                        gridViewImage.refreshDrawableState();
                                        gridViewImage.requestLayout();
                                        gridViewImage.refreshDrawableState();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }

                                    return super.setView(view);
                                    // TODO: 20.12.2022  тут конец выбеленого
                                }

                                private void методКликПоДаннымUpImage(@NonNull AppCompatImageButton appCompatImageButton) {
                                    // TODO: 20.07.2023
                                    appCompatImageButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try{
                                                if (v!=null) {
                                                    //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                    SubClassUploadImageFromSDCars imageFromSDCars=new SubClassUploadImageFromSDCars();
                                                    imageFromSDCars.методUploadImetImage();
                                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                            "materialTextViewЭлементСписка"+  v);
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                        this.getClass().getName(),
                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                        }
                                    });

                                }

                                 // TODO: 21.07.2023
                                 private void методКликПоДаннымCreateImage(@NonNull AppCompatImageButton appCompatImageButton) {
                                     // TODO: 20.07.2023
                                     appCompatImageButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             try{
                                                 if (v!=null) {
                                                     //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                     SubClassUploadImageFromSDCars imageFromSDCars=new SubClassUploadImageFromSDCars();
                                                     imageFromSDCars.методCreateImetImage();
                                                     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                             "materialTextViewЭлементСписка"+  v);
                                                     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                             "materialTextViewЭлементСписка"+  v);
                                                 }
                                             } catch (Exception e) {
                                                 e.printStackTrace();
                                                 Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                 new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                         this.getClass().getName(),
                                                         Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                             }
                                         }
                                     });

                                 }
                            }
                                    .setTitle("Новые фотографии")
                                    .setCancelable(false)
                                    .setIcon( R.drawable.icon_for_create_image2)
                                    .setView(getLayoutInflater().inflate( R.layout.simple_for_new_search_create_image, null )).show();
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                            layoutParams.copyFrom(   alertDialogCreateImage.getWindow().getAttributes());
                            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                            layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;
                         /*   layoutParams.width = 600;
                            layoutParams.height =1000;*/
                            layoutParams.gravity = Gravity.CENTER;
                            alertDialogCreateImage.getWindow().setAttributes(layoutParams);

                            // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +" binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

// TODO: 20.07.2023 при созданнии или Upload Image
                    private void методЗакрываемСозданиеИлиUpIamge(@NonNull  MaterialButton materialButtonЗакрытьДиалог,
                                                                   @NonNull AlertDialog alertDialogCreateImage ) {
                        materialButtonЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    Log.d(this.getClass().getName()," Image Update and Create Image");
                                    alertDialogCreateImage.dismiss();
                                    alertDialogCreateImage.cancel();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }

            }  //todo END SubClassUploadImageAnFragmentImage


        // TODO: 20.07.2023 класс ЗагрузкиImage из Хранилишща Телефона
        class SubClassUploadImageFromSDCars{
            CameraDevice cameraDevice;
      void методUploadImetImage(){
    try{
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);
        // TODO: 20.07.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                  +" bitmap "+ bitmap);
      } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            }
// TODO: 21.07.2023 create

            void методCreateImetImage(){
                try{
               String NameNewImage=     "new photo material"+String.valueOf(new Random().nextInt());
                    ContentValues values=new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE,NameNewImage);
                    values.put(MediaStore.Images.Media.DESCRIPTION,"photo matetial");
                    Uri uri_image=getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,uri_image);
                    startActivityForResult(intent,200);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            +" bitmap "+ bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }

        }

    }//todo END SubClassCreateNewImageForMateril
}
