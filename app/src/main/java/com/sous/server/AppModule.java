package com.sous.server;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
    public class AppModule {

        Application mApplication;

        public AppModule(Application application) {
            mApplication = application;
        }

        @Provides
        @Singleton
        Application providesApplication() {
            return mApplication;
        }
    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    String ggga(Application application) {
        return new Date().toLocaleString();
    }
    }

