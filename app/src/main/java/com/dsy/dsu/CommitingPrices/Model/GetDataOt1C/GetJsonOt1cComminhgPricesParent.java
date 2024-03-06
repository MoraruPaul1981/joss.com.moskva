package com.dsy.dsu.CommitingPrices.Model.GetDataOt1C;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.LinkedHashMap;

import io.reactivex.rxjava3.annotations.NonNull;

public abstract  class GetJsonOt1cComminhgPricesParent {

  public abstract byte[] getByteComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId,
                                                           @NotNull ObjectMapper objectMapper);

  public abstract String getStringComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId,
                                                 @NotNull ObjectMapper objectMapper);


}
