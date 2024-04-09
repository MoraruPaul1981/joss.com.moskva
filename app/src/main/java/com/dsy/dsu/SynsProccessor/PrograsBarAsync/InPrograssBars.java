package com.dsy.dsu.SynsProccessor.PrograsBarAsync;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface InPrograssBars {

    void setAsyncrograssbarMap(@NonNull LinkedHashMap<String, Long> linkedHashMap, @NonNull String имяТаблицаAsync);
    void setAsyncrograssbarList( @NonNull CopyOnWriteArrayList<String>   NameTableAsync,@NonNull String имяТаблицаAsync);
    void методCallBackPrograssBars(@NonNull int Проценны, @NonNull String имяТаблицаAsync, @NonNull Integer ПозицияТекущейТаблицы, @NonNull int  maxAllCountRow);

}
