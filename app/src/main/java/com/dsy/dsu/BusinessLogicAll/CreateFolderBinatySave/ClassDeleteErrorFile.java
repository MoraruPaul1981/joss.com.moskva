package com.dsy.dsu.BusinessLogicAll.CreateFolderBinatySave;


import android.content.Context;
import android.util.Log;

public class ClassDeleteErrorFile extends  ClassDeleteFiles{


    public ClassDeleteErrorFile(Context context) {
        super(context);
    }

    @Override
    public void МетодDeleteFolders() {
     fileName = "Sous-Avtodor-ERROR.txt";
    patchFileName="SousAvtoFile";
        // TODO: 25.03.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.МетодDeleteFolders();
    }
}





