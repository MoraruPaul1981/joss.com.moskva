package com.dsy.dsu.Code_For_AdmissionMaterials.Window;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;

import javax.annotation.Nullable;


public class FragmentCamera extends DialogFragment {
    private   Toolbar toolbarCamera;
    private ImageButton imageButtonCameraback;
    private MaterialButton button_create_new_image;

    private  BisinessLogica bisinessLogica;
    public FragmentCamera() {
        // Required empty public constructor
    }

    public static FragmentCamera newInstance() {
        Bundle args = new Bundle();
        FragmentCamera fragment = new FragmentCamera();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            bisinessLogica=new BisinessLogica();
            // TODO: 20.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot=null;
        try{
            viewRoot= inflater.inflate(R.layout.fragment_camera, container, false);
            imageButtonCameraback=(ImageButton) viewRoot.findViewById(R.id.imageButtonCameraback);
            
            button_create_new_image=(MaterialButton) viewRoot.findViewById(R.id.button_create_new_image);
            bisinessLogica.  методСлушатели();
            // TODO: 20.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return viewRoot;
    }



    @Override
    public void onResume() {
        super.onResume();
        try{
           bisinessLogica. методНастройкиФИнаногоВидаКамеры();
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




    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }



    // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica
    public   class BisinessLogica {


        private void методСлушатели() {
            imageButtonCameraback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023

                    getDialog().dismiss();
                    getDialog().cancel();

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                }
            });
            button_create_new_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                }
            });
        }

        private void методНастройкиФИнаногоВидаКамеры() {
            try{
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(    getDialog().getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                 layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;
                //layoutParams.height =1350;
                layoutParams.gravity = Gravity.CENTER;
                getDialog().getWindow().setAttributes(layoutParams);
                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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




    }//TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica

}
