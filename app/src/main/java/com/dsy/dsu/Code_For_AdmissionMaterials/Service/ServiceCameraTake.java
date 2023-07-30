package com.dsy.dsu.Code_For_AdmissionMaterials.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class ServiceCameraTake extends Service {
    public LocalBinderCamera binder = new LocalBinderCamera();

    private Handler handlerbackgroupCamera;
    private Animation animationscroll;
    private  CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> copyOnWriteArrayListSuccessAddImages =new CopyOnWriteArrayList<>();
    private  CopyOnWriteArrayList <ImageView>copyOnWriteArrayListGetImages=new CopyOnWriteArrayList<>();



    // TODO: 30.07.2023 new varivable
    protected  final int CAMERA_CALIBRATION_DELAY = 5000;
    protected final String TAG = "myLog";
    protected  final int CAMERACHOICE = CameraCharacteristics.LENS_FACING_BACK;
    protected long cameraCaptureStartTime;
    protected CameraCaptureSession session;
    private CameraManager cameraManager;
    private CameraDevice cameraDevice;
    private ImageReader imageReader;

    @Override
    public void onCreate() {
        super.onCreate();
        try{
        // TODO: 28.07.2023
        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            animationscroll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_scrolls);
            // TODO: 28.07.2023
            методHandlerCamera();

            new SubClassCreateNewImageFromCamera().    readyCamera();
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cameraManager " +cameraManager  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



    @Override
    public void onDestroy() {
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        super.onDestroy();
    }

    public class LocalBinderCamera extends Binder {
        public ServiceCameraTake getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cameraManager " +cameraManager  );
            return ServiceCameraTake.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cameraManager " +cameraManager  );
        return  binder ;
    }


    // TODO: 29.07.2023  main bound metod

   public CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> метоСлужбыTakePhotos(@NonNull Intent intent, @NonNull CopyOnWriteArrayList <ImageView>copyOnWriteArrayListGetImages ){
       try{
           this.copyOnWriteArrayListGetImages=copyOnWriteArrayListGetImages;
           // TODO: 29.07.2023 Up Photos
            if(intent.getAction().equalsIgnoreCase("ServiceCameraTake.UpImage")){
                    // TODO: 28.07.2023 вариан первый #1 UP Photo
                copyOnWriteArrayListSuccessAddImages =      new SubClassCompleteNewImageUpAndCreate().методОбраобткиUPCompleteImages(intent);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages + " intent.getAction() " +intent.getAction());
            }else {
                if(intent.getAction().equalsIgnoreCase("ServiceCameraTake.NewFromCameraImage")){
                    // TODO: 28.07.2023 вариан первый #1 UP Photo
                    copyOnWriteArrayListSuccessAddImages =      new SubClassCreateNewImageFromCamera().методСозданиеNewImageForCamera(intent);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages+ " intent.getAction() " +intent.getAction());
                }



            }






                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            return copyOnWriteArrayListSuccessAddImages;
    }








    public  void методHandlerCamera(){
        try{
            handlerbackgroupCamera=    new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }







// TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА
    class SubClassCompleteNewImageUpAndCreate{
    CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> методОбраобткиUPCompleteImages(  @Nullable Intent intentUpPhotos  ){
            try{
                // TODO: 24.07.2023
                if (intentUpPhotos!=null) {
                    Uri selectedImage = intentUpPhotos.getData();
                    InputStream stream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                    BufferedInputStream bufferedInputStreamUpLoadImage=new BufferedInputStream(stream);
                    Bitmap bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);
                    // TODO: 24.07.2023
                    copyOnWriteArrayListSuccessAddImages =            методЗаполянемImageViewNewImage(bitmapUpImage);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n"+ "  bitmapUpImage " +bitmapUpImage  + " copyOnWriteArrayListSuccessAddImages.size() " + copyOnWriteArrayListSuccessAddImages.size() );
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return copyOnWriteArrayListSuccessAddImages;
        }
        void методОбраобткиUPCompleteImages(@NonNull Context context,  @Nullable Uri uriNewImageCamera  ){
            try{
                // TODO: 24.07.2023
                if (uriNewImageCamera!=null) {
                    InputStream stream = getApplicationContext().getContentResolver().openInputStream(uriNewImageCamera);
                    BufferedInputStream bufferedInputStreamUpLoadImage=new BufferedInputStream(stream);
                    Bitmap   bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);
                    // TODO: 24.07.2023
                   // методЗаполянемImageViewNewImage(bitmapUpImage);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n"+ "  bitmapUpImage " +bitmapUpImage  );
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 24.07.2023  заполяем дааныых
        private CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> методЗаполянемImageViewNewImage(@NonNull  Bitmap   bitmapUpImage ) {
            try{
                final boolean[] ФлагЧтоУжеОднаВставкаУжеБыла = {false};
                Flowable.fromIterable(copyOnWriteArrayListGetImages)
                        .onBackpressureBuffer(copyOnWriteArrayListGetImages.size(),
                                null, BackpressureOverflowStrategy.ERROR)
                        .repeatWhen(repeat->repeat.delay(200, TimeUnit.MILLISECONDS))
                        .takeWhile(new Predicate<ImageView>() {
                            @Override
                            public boolean test(ImageView imageView) throws Throwable {
                                if (ФлагЧтоУжеОднаВставкаУжеБыла[0]==false ) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]  );
                                    return true;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]);
                                    return false;
                                }

                            }
                        })
                        .filter( image->image.getDrawingCache()==null)
                        .doOnNext(new Consumer<ImageView>() {
                            @Override
                            public void accept(ImageView imageView) throws Throwable {
                                try {
                                    // TODO: 25.07.2023  добавление новго Image
                                    Long УжеЕслиТАкойIDImage=
                                            copyOnWriteArrayListSuccessAddImages.stream()
                                                    .filter(s->s.containsKey(imageView.getId())).collect(Collectors.counting());

                                    if (УжеЕслиТАкойIDImage==0) {
                                        // TODO: 24.07.2023  set Image
                                        методВставкиImageGenerator(imageView, bitmapUpImage);
                                        // TODO: 25.07.2023 ставим флаг что вставка одна успешно стработал
                                        ФлагЧтоУжеОднаВставкаУжеБыла[0] = true;


                                        методЗапоелнияУжеДобавленыхImage(imageView,bitmapUpImage);


                                    }

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " bitmapUpImage " +bitmapUpImage + " imageView.getId() " +imageView.getId() + " УжеЕслиТАкойIDImage " +УжеЕслиТАкойIDImage);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                                }
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                                return false;
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 24.07.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .subscribe();


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }


        private void методВставкиImageGenerator(@NonNull  ImageView imageView, @NonNull Bitmap bitmapUpImage) {
            try{
                imageView.setImageBitmap(bitmapUpImage);
                imageView.startAnimation(animationscroll);
                imageView.refreshDrawableState();
                imageView.requestLayout();
                Log.d(getApplicationContext().getClass().getName(), "\n" + " alertDialogCreateImage ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void методЗапоелнияУжеДобавленыхImage( @NonNull ImageView imageView , @NonNull Bitmap bitmapUpImage) {
            try{
                LinkedHashMap<Integer,Bitmap>  linkedHashMapCompeleImage=    new LinkedHashMap<>();
                linkedHashMapCompeleImage.put(   imageView.getId()     , bitmapUpImage);
                copyOnWriteArrayListSuccessAddImages.add(linkedHashMapCompeleImage);

                Log.d(getApplicationContext().getClass().getName(), "\n" + " copyOnWriteArrayListSuccessAddImages "
                        + copyOnWriteArrayListSuccessAddImages);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext() ).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    } // TODO: 24.07.2023  end SubClassCompleteNewImageUpAndCreate


    
    
    
    
    
    
    
// TODO: 30.07.2023 ВТорой КЛАСС Уже ДЛЯ СОЗДАНИЕ НОВОЙ ФОТОГРАФИИ ЧЕРЕЗ КАМЕРУ
    class  SubClassCreateNewImageFromCamera{







        // TODO: 30.07.2023  New Create From Camera Image
        CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>>  методСозданиеNewImageForCamera(@NonNull Intent intentCreateNewCamera){
 try{
    /* readyCamera();*/
            // TODO: 20.07.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n"
                    + " copyOnWriteArrayListSuccessAddImages.size() "
                    + copyOnWriteArrayListSuccessAddImages.size()
                    + " intentCreateNewCamera " +intentCreateNewCamera );

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getApplicationContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
 return  copyOnWriteArrayListSuccessAddImages;
        }






    // TODO: 30.07.2023  STARTing class two create image photos  code

        protected CameraDevice.StateCallback cameraStateCallback = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice camera) {
                Log.d(TAG, "CameraDevice.StateCallback onOpened");
                cameraDevice = camera;
                actOnReadyCameraDevice();
            }

            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {
                Log.w(TAG, "CameraDevice.StateCallback onDisconnected");
            }

            @Override
            public void onError(@NonNull CameraDevice camera, int error) {
                Log.e(TAG, "CameraDevice.StateCallback onError " + error);
            }
        };

        protected CameraCaptureSession.StateCallback sessionStateCallback = new CameraCaptureSession.StateCallback() {

            @Override
            public void onReady(CameraCaptureSession session) {
                session = session;
                try {
                    session.setRepeatingRequest(createCaptureRequest(), new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
                            super.onCaptureStarted(session, request, timestamp, frameNumber);
                        }

                        @Override
                        public void onCaptureProgressed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureResult partialResult) {
                            super.onCaptureProgressed(session, request, partialResult);
                        }

                        @Override
                        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                            super.onCaptureCompleted(session, request, result);
                        }

                        @Override
                        public void onCaptureFailed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureFailure failure) {
                            super.onCaptureFailed(session, request, failure);
                            session.close();
                        }

                        @Override
                        public void onCaptureSequenceCompleted(@NonNull CameraCaptureSession session, int sequenceId, long frameNumber) {
                            super.onCaptureSequenceCompleted(session, sequenceId, frameNumber);
                        }

                        @Override
                        public void onCaptureSequenceAborted(@NonNull CameraCaptureSession session, int sequenceId) {
                            super.onCaptureSequenceAborted(session, sequenceId);
                        }

                        @Override
                        public void onCaptureBufferLost(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull Surface target, long frameNumber) {
                            super.onCaptureBufferLost(session, request, target, frameNumber);
                        }
                    }, handlerbackgroupCamera);
                    cameraCaptureStartTime = System.currentTimeMillis ();


                } catch (CameraAccessException e) {
                    Log.e(TAG, e.getMessage());
                }
            }


            @Override
            public void onConfigured(CameraCaptureSession session) {

            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            }
        };

        protected ImageReader.OnImageAvailableListener onImageAvailableListener = new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Log.d(TAG, "onImageAvailable");
                Image img = reader.acquireLatestImage();
                if (img != null) {
                    if (System.currentTimeMillis () > cameraCaptureStartTime + CAMERA_CALIBRATION_DELAY) {
                        processImage(img);
                    }
                    img.close();
                    reader.close();
                }
            }
        };

        public void readyCamera() {

            try {
                String pickedCamera = getCamera(cameraManager);
                cameraManager.openCamera(pickedCamera, cameraStateCallback, handlerbackgroupCamera);
                imageReader = ImageReader.newInstance(1920, 1088, ImageFormat.JPEG, 2 /* images buffered */);
                imageReader.setOnImageAvailableListener(onImageAvailableListener, handlerbackgroupCamera);
                Log.d(TAG, "imageReader created");
            } catch (CameraAccessException e){
                Log.e(TAG, e.getMessage());
            }
        }

        public String getCamera(CameraManager manager){
            try {
                for (String cameraId : manager.getCameraIdList()) {
                    CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                    int cOrientation = characteristics.get(CameraCharacteristics.LENS_FACING);
                    if (cOrientation == CAMERACHOICE) {
                        return cameraId;
                    }
                }
            } catch (CameraAccessException e){
                e.printStackTrace();
            }
            return null;
        }




        public void actOnReadyCameraDevice()
        {
            try {
                cameraDevice.createCaptureSession(Arrays.asList(imageReader.getSurface()), sessionStateCallback, handlerbackgroupCamera);
            } catch (CameraAccessException e){
                Log.e(TAG, e.getMessage());
            }
        }




        private void processImage(Image image){
            //Process image data
            ByteBuffer buffer;
            byte[] bytes;
            boolean success = false;
            File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/image.jpg");
            FileOutputStream output = null;

            if(image.getFormat() == ImageFormat.JPEG) {
                buffer = image.getPlanes()[0].getBuffer();
                bytes = new byte[buffer.remaining()]; // makes byte array large enough to hold image
                buffer.get(bytes); // copies image from buffer to byte array
                try {
                    output = new FileOutputStream(file);
                    output.write(bytes);    // write the byte array to file
                    success = true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    image.close(); // close this to free up buffer for other images
                    if (null != output) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }

        protected CaptureRequest createCaptureRequest() {
            try {
                CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
                builder.addTarget(imageReader.getSurface());
                return builder.build();
            } catch (CameraAccessException e) {
                Log.e(TAG, e.getMessage());
                return null;
            }
        }

    // TODO: 30.07.2023  AND class two create image photos  code

    } // TODO: 30.07.2023  //END CLASS SubClassCreateNewImageFromCamera













    }//TODO END ServiceCameraTake





