package com.dsy.dsu.CommitPrices.Model.GetDataOt1C;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

public abstract  class GetJsonOt1cComminhgPricesParent {

  public abstract byte[] getByteComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId,
                                                           @NotNull ObjectMapper objectMapper);

  public abstract String getStringComminhgPrices(@NotNull Context context, @NotNull String adress, @NotNull Integer PublicId,
                                                 @NotNull ObjectMapper objectMapper);


}
