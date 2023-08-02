package com.dsy.dsu.Code_For_AdmissionMaterials.Window;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.VideoCaptureConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.os.Environment;
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
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;


public class FragmentCamera extends DialogFragment {
    private   Toolbar toolbarCamera;
    private ImageButton imageButtonCameraback;
    private MaterialButton button_create_new_image;
    private  BisinessLogica bisinessLogica;
    private  PreviewView preview_view;
    private ImageCapture imageCapture;
    public static final int CAMERA_PERSSION_CODE=1;

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
            bisinessLogica.    методДаемПраваНаCameraPermissions();
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




            // TODO: 02.08.2023  TEST CODE

            File file = new File(Environment.getExternalStorageDirectory()+"/HeyThisISJayuir.jpg");

            ImageCapture.OutputFileOptions outputFileOptions=new ImageCapture.OutputFileOptions.Builder(file).build();


            // TODO: 02.08.2023  TEst Code Video

            Preview preview = new Preview.Builder().build();
            PreviewView preview_view =viewRoot. findViewById(R.id.preview_view);

            bisinessLogica.    setCameraProviderListener();


            // TODO: 20.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " preview_view " +preview_view );


/*// The use case is bound to an Android Lifecycle with the following code
            Camera camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview);

// PreviewView creates a surface provider, using a Surface from a different
// kind of view will require you to implement your own surface provider.
            preview. = viewFinder.getSurfaceProvider();*/





            // TODO: 02.08.2023  TEst Code Video 2

/*
         VideoCapture videoCapture = VideoCaptureConfig.Builder()     // <--- Builder is in red
                    .setTargetRotation(binding.previewView.display.rotation)
                    .setCameraSelector(cameraSelector)
                    .setTargetAspectRatio(screenAspectRatio)
                    .build();*/














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
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

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
                getDialog().setCancelable(false);
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
        public  void методДаемПраваНаCameraPermissions(){
            if (ContextCompat.checkSelfPermission(getActivity(),android. Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                Log.d("checkCameraPermissions", "No Camera Permissions");
                //////////////////////TODO SERVICE
                String[] permissions = new String[]{
                        android. Manifest.permission.CAMERA,
                        android. Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.INTERNET,
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.VIBRATE,
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
                ActivityCompat.requestPermissions(getActivity(), permissions, CAMERA_PERSSION_CODE);


            }else{
                // Permission is not granted
                Log.d("checkCameraPermissions", "Success YRA  Camera Permissions  !!!!");
            }
        }

        private void setCameraProviderListener() {
            ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                    ProcessCameraProvider.getInstance(requireContext());
            cameraProviderFuture.addListener(() -> {

                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    // No errors need to be handled for this Future
                    // This should never be reached
                    e.printStackTrace();
                }
            }, ContextCompat.getMainExecutor(requireContext()));
        }

        private void bindPreview(ProcessCameraProvider cameraProvider) {

            CameraSelector cameraSelector =
                    new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

            Preview preview = new Preview.Builder().build();

            preview.setSurfaceProvider(preview_view.getSurfaceProvider());

            ViewPort viewPort = preview_view.getViewPort();


        }




    }//TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica

}
