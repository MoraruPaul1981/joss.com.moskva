package com.dsy.dsu.BootAndAsync.BlBootAsync;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicAll.CreateFolderBinatySave.ClassCreateFolders;

public class ClassCreateFolderUpdatePO extends ClassCreateFolders {
    public ClassCreateFolderUpdatePO(Context context) {
        super(context);
    }

    @Override
    public void МетодCreateFoldersBinaty() {

        patchFileName="SousAvtoFile/UpdatePO";
        // TODO: 25.03.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.МетодCreateFoldersBinaty();

    }
}
