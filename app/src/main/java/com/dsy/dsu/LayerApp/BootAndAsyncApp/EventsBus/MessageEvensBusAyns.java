package com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusAyns {

    public Intent mess;


    public MessageEvensBusAyns( @NonNull Intent mess) {
        this.mess = mess;
    }
}
