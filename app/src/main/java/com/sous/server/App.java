package com.sous.server;

import android.app.Application;

import javax.inject.Inject;

public class App extends Application {
    @Inject
    AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }
    public AppComponent getAppComponent() {
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
        return mAppComponent;
    }
}