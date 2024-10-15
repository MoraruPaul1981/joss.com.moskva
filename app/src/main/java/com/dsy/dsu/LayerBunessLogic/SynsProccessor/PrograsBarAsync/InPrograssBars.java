package com.dsy.dsu.LayerBunessLogic.SynsProccessor.PrograsBarAsync;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public interface InPrograssBars {

    void setAsyncrograssbarMap(@NonNull LinkedHashMap<String, Long> linkedHashMap, @NonNull String имяТаблицаAsync,@NonNull Integer SuccessInsertOrUpdates);
    void setAsyncrograssbarList( @NonNull CopyOnWriteArrayList<String>   NameTableAsync,@NonNull String имяТаблицаAsync, @NonNull Integer SuccessInsertOrUpdates);
    void методCallBackPrograssBars(@NonNull int Проценны, @NonNull String имяТаблицаAsync, @NonNull Integer ПозицияТекущейТаблицы, @NonNull int  maxAllCountRow,@NonNull    Integer SuccessInsertOrUpdates);

}
