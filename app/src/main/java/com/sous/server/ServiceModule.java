package com.sous.server;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import com.sous.server.Services.ServiceForServerScannerAsync;

import javax.inject.Inject;

@Module
public
class ServiceModule {
@Inject
    ServiceForServerScannerAsync mService;

    public ServiceModule(ServiceForServerScannerAsync service) {
        mService = service;
    }

    @Provides
    ServiceForServerScannerAsync provideMyService() {
        return mService;
    }
}