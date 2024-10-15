package com.dsy.dsu.LayerApp.AdmissionMaterialsApp.Window;

import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;

public interface CameraXInterface {
    Bitmap onGetFinishEditDialogNewPhotos( ) ;

    void onSEtFinishEditDialogNewPhotos(@NotNull Bitmap bitmap) ;
}
