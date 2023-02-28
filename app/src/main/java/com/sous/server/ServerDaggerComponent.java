package com.sous.server;

import com.google.android.datatransport.runtime.dagger.Component;
import com.sous.server.Services.ServiceForServerScannerAsync;


@Component(modules= ServerDaggerModules.class)
public interface ServerDaggerComponent {
    void inject(ServiceForServerScannerAsync.LocalBinderAsyncServer binderAsyncServer);
}
