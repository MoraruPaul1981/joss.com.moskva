package com.sous.server;

import com.google.android.datatransport.runtime.dagger.Component;
import com.sous.server.Services.ServiceForServerScannerAsync;

@Component(modules=ServiceModule.class)
interface MyServiceComponent {
    void inject(ServiceForServerScannerAsync service);

    ServiceForServerScannerAsync ServiceModule();
}
