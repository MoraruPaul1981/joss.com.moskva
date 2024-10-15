package com.dsy.dsu.LayerBunessLogic.Hilt.OkhhtpBuilder.getOkhhtps;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Hilt.OkhhtpBuilder.interfaces.InGetOkhhtpBuilder;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class GetOkhhtpBuilderSSLWithCert implements InGetOkhhtpBuilder {
    Context context;

    public GetOkhhtpBuilderSSLWithCert(@NotNull Context context ) {
        this.context = context;
    }

    @Override
    public OkHttpClient.Builder getOkhhtpBuilder() {
        OkHttpClient.Builder builder=null;
        try{

// Install the all-trusting trust manager
            Dispatcher dispatcher= new Dispatcher(Executors.newCachedThreadPool());
            builder=     new OkHttpClient().newBuilder().dispatcher(dispatcher);
            builder.connectionPool(new ConnectionPool(20, 30, TimeUnit.SECONDS));
            // TODO: 09.10.2024
           ConnectionSpec spec = new ConnectionSpec.Builder( ConnectionSpec.MODERN_TLS)
                    .allEnabledTlsVersions()
                    .allEnabledCipherSuites()
                    .build();
            builder.connectionSpecs(  ( Arrays.asList(spec,ConnectionSpec.CLEARTEXT,spec)));
            builder.retryOnConnectionFailure(false);

            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add("192.168.3.4", "sha256/341a7062a34c0740f00d73f3e921a8d68305c90dd1f43ef41546c2684f60ca1")
                    .build();
            builder.certificatePinner(certificatePinner);



             KeyStore keyStore = KeyStore.getInstance("BKS");
            InputStream instream = context.getResources().openRawResource(R.raw.bksbasedsu1ru);
            keyStore.load(instream, "mypassword".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");//TLSv1.3
            sslContext.init(null, tmf.getTrustManagers(),  new SecureRandom());
            builder.setSocketFactory$okhttp(sslContext.getSocketFactory());

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

