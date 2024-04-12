package com.dsy.dsu.Settings.Model.BL;

import android.content.Context;

import androidx.annotation.NonNull;

public interface INSetttingTabels {


   Long getWritingPasswordSetingTable(@NonNull Context context, @NonNull Integer PublicID);

   Long getWritingOneSingalSetingTable(@NonNull Context context, @NonNull String НовыйIdОТСервтераOneSignal,@NonNull Integer PublicID);

}
