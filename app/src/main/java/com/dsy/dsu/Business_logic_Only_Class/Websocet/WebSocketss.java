package com.dsy.dsu.Business_logic_Only_Class.Websocet;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.onesignal.TLS12SocketFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketss {
    OkHttpClient okHttpClient;
    public class  классИнициализацииКлиентаWebSocerts{
   public   void методИнициализацииwebsocets (@NonNull Context context){
         try{
             okHttpClient=new OkHttpClient();
             Request request=new Request.Builder().url("ws://192.168.254.40:8080/jboss-1.0-SNAPSHOT/gEt").build();
             //Request request=new Request.Builder().url("ws://192.168.254.40:8080/websocet/gEt").build();
            // Request request=new Request.Builder().url("ws://localhost:8080/jboss-1.0-SNAPSHOT/gEt").build();
             WebSocket wsandroid=okHttpClient.newWebSocket(request, new WebSocketListener() {
                 @Override
                 public void onOpen(WebSocket webSocket, Response response) {
                     super.onOpen(webSocket, response);
                     webSocket.send("Android WebSocets !!!"+new Date().toLocaleString());
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }

                 @Override
                 public void onMessage(WebSocket webSocket, String text) {
                     super.onMessage(webSocket, text);
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }

                 @Override
                 public void onMessage(WebSocket webSocket, ByteString bytes) {
                     super.onMessage(webSocket, bytes);
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }

                 @Override
                 public void onClosing(WebSocket webSocket, int code, String reason) {
                     super.onClosing(webSocket, code, reason);
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }

                 @Override
                 public void onClosed(WebSocket webSocket, int code, String reason) {
                     super.onClosed(webSocket, code, reason);
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }

                 @Override
                 public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                     super.onFailure(webSocket, t, response);
                     Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                 }
             });
             okHttpClient.dispatcher().executorService().shutdown();

         Log.d(this.getClass().getName(), "   методДляТетсирования1С");

     } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        }

    }

    // TODO: 06.06.2023  КЛАСС okHHTP обычный ЗАПрпоса

    public class  ClassOkHttpОбычныйПинг {

        public  void методОбычногоПодключениявOkHttp(@NonNull Context context) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder();
                                Request newRequest = builder.build();
                                return chain.proceed(newRequest);
                            }
                        }).connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(100, TimeUnit.SECONDS).build();
                Request requestGET = new Request.Builder().get().url("http://192.168.254.40:8080/websocet/gEt").build();////"http://192.168.254.40:8080/websocet/gEt"
                Log.d(this.getClass().getName(), "  request  " + requestGET);
                okHttpClient.newCall(requestGET).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        okHttpClient.dispatcher().executorService().shutdown();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            stringBuffer.append(response.body().string());
                            Log.d(this.getClass().getName(), "  stringBuffer  " + stringBuffer.toString() + "  responseGet.code()" + response.code());
                        }
                        //TODO закрываем п отоки
                        okHttpClient.dispatcher().executorService().shutdown();
                    }
                });

                okHttpClient.dispatcher().executorService().awaitTermination(20, TimeUnit.SECONDS);
                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString() );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }

    // TODO: 06.06.2023  end   class классИнициализацииКлиентаWebSocerts
}
