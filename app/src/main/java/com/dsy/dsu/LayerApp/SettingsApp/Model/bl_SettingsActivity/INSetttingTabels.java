package com.dsy.dsu.LayerApp.SettingsApp.Model.bl_SettingsActivity;

import android.content.Context;

import androidx.annotation.NonNull;

public interface INSetttingTabels {


   Integer getWritingPasswordSetingTable(@NonNull Context context, @NonNull Integer PublicID);

   Integer getWritingOneSingalSetingTable(@NonNull Context context, @NonNull String НовыйIdОТСервтераOneSignal,@NonNull Integer PublicID);

}
