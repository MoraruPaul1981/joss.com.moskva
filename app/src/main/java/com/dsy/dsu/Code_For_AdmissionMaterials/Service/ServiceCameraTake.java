package com.dsy.dsu.Code_For_AdmissionMaterials.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
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
    private CameraManager cameraManager;
    private TextureView textureView;
    private CameraCaptureSession cameraCaptureSession;
    private CameraDevice cameraDevice;
    private CaptureRequest captureRequest;
    private ImageReader imageReader;
    private Handler handlerbackgroupCamera;
    private  String cameraId;
    private  CameraCharacteristics characteristics ;
    private Animation animationscroll;
    private  CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> copyOnWriteArrayListCompleteImageWithID;
 


    @Override
    public void onCreate() {
        super.onCreate();
        try{
        // TODO: 28.07.2023
        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            animationscroll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_scrolls);
            // TODO: 28.07.2023
            методHandlerCamera();
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

   public void методБиндингаСлужбыКамера(@NonNull Intent intent){
            if(intent.getAction().equalsIgnoreCase("StartServiceCamera.takephoto")){
                try{
                    // TODO: 28.07.2023 вариан первый #1
                    методЗапускаServiceCamera(        new ClassTakeCameraBusinessTwo());

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
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


    // TODO: 28.07.2023  запувск класса по созданию ФОТО #1
    void  методЗапускаServiceCamera(@NonNull ClassTakeCameraBusinessTwo classTakeCameraBusiness){
        try{

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch ( Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


















// TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА



    class ClassTakeCameraBusiness {/*

    public  void методOpenCamera(){
        try{
            cameraManager.openCamera(cameraId, new CameraDevice.StateCallback() {
                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }

                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    try{
                        cameraDevice = camera;
                        createCaptureSession();
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
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
                public void onError(@NonNull CameraDevice camera, int error) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            }, handlerbackgroupCamera);
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

        public  void методSetupCamera(){
            try{
                getApplicationContext().getMainExecutor().execute(()->{
                    Toast.makeText(ServiceCameraTake.this, " ServiceCameraTake методStartServiceTakePhotoCamera !!!!", Toast.LENGTH_SHORT).show();
                });

                for ( String cameraIdBnytri : cameraManager.getCameraIdList()) {
                     characteristics = cameraManager.getCameraCharacteristics(cameraIdBnytri);
                        cameraId=cameraIdBnytri;
                        imageReader = ImageReader.newInstance(800, 900, ImageFormat.JPEG, 2);
                        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                            @Override
                            public void onImageAvailable(ImageReader reader) {
                                try{
                                    Image image = reader.acquireLatestImage();
                                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                                    byte[] bytes = new byte[buffer.remaining()];
                                    buffer.get(bytes);
                                    image.close();
                                    Log.d(getApplicationContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        },handlerbackgroupCamera);

                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private final ImageReader.OnImageAvailableListener onImageAvailableListener = new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {




                imageReader = ImageReader.newInstance(800, 900, ImageFormat.JPEG, 2);
                imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        try{
                            Image image = reader.acquireLatestImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            image.close();
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                },handlerbackgroupCamera);





                createCaptureRequest();
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        };

        void createCaptureSession() {
        List<Surface> outputSurfaces = new LinkedList<>();
        outputSurfaces.add(imageReader.getSurface());
        try {
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    cameraCaptureSession = session;

                    imageReader.setOnImageAvailableListener(onImageAvailableListener, handlerbackgroupCamera);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            }, handlerbackgroupCamera);

        } catch (CameraAccessException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


        private void createCaptureRequest() {
            try {

                CaptureRequest.Builder requestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                requestBuilder.addTarget(imageReader.getSurface());

                // Focus
                requestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

                // Orientation
                requestBuilder.set(CaptureRequest.JPEG_ORIENTATION,  100);

                cameraCaptureSession.capture(requestBuilder.build(), new CameraCaptureSession.CaptureCallback() {
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

            } catch (CameraAccessException e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    */}

    class ClassTakeCameraBusinessTwo {

    }// TODO: 29.07.2023  /// END ClassTakeCameraBusinessTwo


    class SubClassCompleteNewImageUpAndCreate{
        void методОбраобткиUPCompleteImages(@NonNull Context context,  @Nullable Intent data  ){
            try{
                // TODO: 24.07.2023
                if (data!=null) {
                    Uri selectedImage = data.getData();
                    InputStream stream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                    BufferedInputStream bufferedInputStreamUpLoadImage=new BufferedInputStream(stream);
                    Bitmap bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);
                    // TODO: 24.07.2023
                    //методЗаполянемImageViewNewImage(bitmapUpImage);
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
        private void методЗаполянемImageViewNewImage(@NonNull  Bitmap   bitmapUpImage
                , @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages,
                                                     @NonNull CopyOnWriteArrayList<LinkedHashMap<Integer,Bitmap>> copyOnWriteArrayListCompleteImageWithID) {
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
                                            copyOnWriteArrayListCompleteImageWithID.stream()
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
                copyOnWriteArrayListCompleteImageWithID.add(linkedHashMapCompeleImage);

                Log.d(getApplicationContext().getClass().getName(), "\n" + " copyOnWriteArrayListCompleteImageWithID "
                        + copyOnWriteArrayListCompleteImageWithID );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext() ).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        void методобработкиSimpleCreateImage(@NonNull Context context, @Nullable Intent data ){
            try{
                Bitmap      bitmapCreate_New_CameraImage= null;
                Long UUIDGeneratorImage= null;
                // TODO: 25.07.2023  Записываем Созданое Новое Image через Камеру
                if (data!=null) {
                    Bundle bundleGetImages=(Bundle)   data.getExtras();
                    // TODO: 24.07.2023 Создание Нового Image  c сохранение

                    bitmapCreate_New_CameraImage = (Bitmap) bundleGetImages.get("data");
                    // TODO: 20.07.2023
                 //  методЗаполянемImageViewNewImage(bitmapCreate_New_CameraImage);
                }

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "  bitmapCreate_New_CameraImage " +bitmapCreate_New_CameraImage +
                        " UUIDGeneratorImage " +UUIDGeneratorImage);
                // TODO: 24.07.2023 Создание Нового Simple  Image  c сохранение

                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        }

    } // TODO: 24.07.2023  end SubClassCompleteNewImageUpAndCreate
















    }//TODO END ServiceCameraTake





