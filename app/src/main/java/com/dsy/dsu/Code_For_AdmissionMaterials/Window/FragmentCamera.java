package com.dsy.dsu.Code_For_AdmissionMaterials.Window;



import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Nullable;


public class FragmentCamera extends DialogFragment  implements CameraXInterface {

    private ImageButton imageButtonCameraback;
    private MaterialButton button_create_new_image;
    private  BisinessLogica bisinessLogica;
    public static final int CAMERA_PERSSION_CODE=1;
    private     ProcessCameraProvider processCameraProvider;
    private ImageCapture imageCapture;
    private     PreviewView preview_view;
    private  ExecutorService mExecutorService;

   private CameraXInterface cameraXInterface;
   private       Camera camera;
    public   Bitmap bitmapNewPhotoFromCameraX;
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
            // TODO: 02.08.2023
            mExecutorService = Executors.newSingleThreadExecutor();
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
             preview_view =viewRoot. findViewById(R.id.preview_view);
            // TODO: 02.08.2023  TEST CODE

            bisinessLogica.new ClassCameraX().    методЗапускаКамерыX();

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
            // TODO: 02.08.2023
          //  getTargetFragment().onAttach(getContext());
           bisinessLogica. методНастройкиФИнаногоВидаКамеры();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            if (context instanceof CameraXInterface) {
                cameraXInterface = (CameraXInterface) context;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cameraXInterface " +cameraXInterface);
            }
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


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        try{
            if(processCameraProvider!=null){
                processCameraProvider.shutdown();
                processCameraProvider.unbindAll();
            }
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
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

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

    }

    @Override
    public Bitmap onGetFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        return bitmap;
    }

    @Override
    public void onSEtFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {
        try{
            bitmap=  bitmapNewPhotoFromCameraX;
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
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


    // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica
    public   class BisinessLogica {


        private void методСлушатели() {
            imageButtonCameraback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023
try{
                    getDialog().dismiss();
                    getDialog().cancel();

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
            button_create_new_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023
                    try {
                    bisinessLogica.new ClassCameraX().  takePicture();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                      //  getActivity().b
                    } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
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
                // Permission is not granted
                Log.d("checkCameraPermissions", "NO NO   Camera Permissions  !!!!");

            }else{
                // Permission is not granted
                Log.d("checkCameraPermissions", "Success YRA  Camera Permissions  !!!!");
            }
        }


        // TODO: 02.08.2023  class start CAMERAX
        class ClassCameraX {
            private void методЗапускаКамерыX() {
                ListenableFuture<ProcessCameraProvider> providerListenableFuture = ProcessCameraProvider.getInstance(getContext());

                providerListenableFuture.addListener(()->{
                    try {
                        while (!providerListenableFuture.isDone());
                        processCameraProvider = providerListenableFuture.get();
                        // TODO: 02.08.2023
                        bindPreview();
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

                }, ContextCompat.getMainExecutor(getContext()));
            }

            private void bindPreview() {
                try{
                imageCapture =
                        new ImageCapture.Builder()
                                .setCameraSelector(CameraSelector.DEFAULT_BACK_CAMERA)
                                .setIoExecutor(mExecutorService)
                                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                                .setTargetRotation(getActivity().getWindowManager().getDefaultDisplay().getRotation())
                                .setUseCaseEventCallback(new UseCase.EventCallback() {
                                    @Override
                                    public void onAttach(@NonNull CameraInfo cameraInfo) {
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " preview_view " +preview_view );
                                    }

                                    @Override
                                    public void onDetach() {
                                        processCameraProvider.shutdown();
                                        processCameraProvider.unbindAll();
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " preview_view " +preview_view );
                                    }
                                }).build();
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(preview_view.getSurfaceProvider());
                //
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
                // bind
              camera = processCameraProvider.bindToLifecycle(getActivity(), cameraSelector, preview, imageCapture);
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




            private void takePicture() {
                try{
                    String NameNewPhotosCamerax="SousAvtoDorPhoto.jpg";
            File     fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator+NameNewPhotosCamerax);
                ImageCapture.OutputFileOptions outputFileOptions =
                        new ImageCapture.OutputFileOptions.Builder(fileNewPhotoFromCameraX).build();
                imageCapture.takePicture(outputFileOptions, mExecutorService,
                        new ImageCapture.OnImageSavedCallback() {
                            @Override
                            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                                System.out.println("SAVED");
                                try{
                                    Uri uri = outputFileResults.getSavedUri();
                                    if(uri != null) {
                                        Toast toast=       Toast.makeText(getContext(),
                                                " Успешно Создание  Фото  !!!", Toast.LENGTH_LONG);
                                        toast.show();

                                        Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                        v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));


                                        ContentResolver cr =getActivity(). getContentResolver();
                                        InputStream inputStream = cr.openInputStream(uri);
                                         bitmapNewPhotoFromCameraX = BitmapFactory.decodeStream(inputStream);
                                  // TODO: 03.08.2023 Получену Фотографию отправляем а Activirty Куда Нужгно
                                        if (bitmapNewPhotoFromCameraX!=null) {
                                            cameraXInterface.onSEtFinishEditDialogNewPhotos(bitmapNewPhotoFromCameraX);
                                        }


                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " uri " +uri + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
                                    }

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " uri " +uri );
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
                            public void onError(ImageCaptureException error) {
                                // TODO: 02.08.2023
                                    ContextCompat.getMainExecutor(getActivity()).execute(()->{
                                        try{
                                            Toast toast=       Toast.makeText(getContext(),
                                                    "Фото не создано !!!", Toast.LENGTH_LONG);
                                            toast.show();
                                                    processCameraProvider.shutdown();
                                        processCameraProvider.unbindAll();
                                            // TODO: 03.08.2023
                                        getDialog().dismiss();
                                        getDialog().cancel();
                                            Log.d(this.getClass().getName(),"\n" + " error " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " error " +error );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                    });
                            }
                        }
                );
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
        }//todo END CLASS ClassCameraX


    }//TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica

}
