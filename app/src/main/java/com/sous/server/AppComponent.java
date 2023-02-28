package com.sous.server;

import android.content.SharedPreferences;

import com.sous.server.Windows.MainActivityNewServerScanner;

import javax.inject.Singleton;

import dagger.Component;

// Dagger will only look for methods annotated with @Provides
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(App app);
    // void inject(MyFragment fragment);
    SharedPreferences providesSharedPreferences();

    String ggga();
    // void inject(MyService service);
}
