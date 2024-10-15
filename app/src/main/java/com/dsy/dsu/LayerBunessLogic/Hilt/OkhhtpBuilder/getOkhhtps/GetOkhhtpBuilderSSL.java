package com.dsy.dsu.LayerBunessLogic.Hilt.OkhhtpBuilder.getOkhhtps;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Hilt.OkhhtpBuilder.interfaces.InGetOkhhtpBuilder;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class GetOkhhtpBuilderSSL implements InGetOkhhtpBuilder {
    Context context;

    public GetOkhhtpBuilderSSL(@NotNull Context context ) {
        this.context = context;
    }

    @Override
    public OkHttpClient.Builder getOkhhtpBuilder() {
        OkHttpClient.Builder builder=null;
        try{
            // TODO: 11.10.2024 POOL
            Dispatcher dispatcher= new Dispatcher(Executors.newCachedThreadPool());
            dispatcher.cancelAll();
            builder=     new OkHttpClient().newBuilder().dispatcher(dispatcher);
            builder.connectionPool(new ConnectionPool(200, 10, TimeUnit.SECONDS));

            // TODO: 06.10.2024 3 вариат
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
// Install the all-trusting trust manager
            final SSLContext sslContext3 = SSLContext.getInstance("TLS");
            sslContext3.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory2 = sslContext3.getSocketFactory();
            // TODO: 09.10.2024
            ConnectionSpec spec = new ConnectionSpec.Builder( ConnectionSpec.MODERN_TLS)
                    .allEnabledTlsVersions()
                    .allEnabledCipherSuites()
                    .build();
            builder.connectionSpecs(  ( Arrays.asList(spec,ConnectionSpec.CLEARTEXT)));
            builder.retryOnConnectionFailure(false);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {

                    return true;
                }

            });

            builder.sslSocketFactory(sslSocketFactory2, (X509TrustManager)trustAllCerts[0]);


            //  builder.setConnectionSpecs$okhttp(new ArrayList<>(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)));
            //  builder.connectionSpecs(Collections.singletonList(spec));
            // builder.connectionSpecs(  ( Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)));
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
        return builder;
    }
    }

