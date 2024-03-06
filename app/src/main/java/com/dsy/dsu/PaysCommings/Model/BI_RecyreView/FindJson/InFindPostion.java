package com.dsy.dsu.PaysCommings.Model.BI_RecyreView.FindJson;

import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InFindPostion {
  @NotNull
  @Nullable
  Integer  startPostionJsonNode(@NotNull JsonNode jsonNode,@NotNull String NumberDoc );
}
