package com.dsy.dsu.Code_For_AdmissionMaterials.Window;

import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;

public interface CameraXInterface {
    Bitmap onGetFinishEditDialogNewPhotos(@NotNull Bitmap bitmap) ;

    void onSEtFinishEditDialogNewPhotos(@NotNull Bitmap bitmap) ;
}
