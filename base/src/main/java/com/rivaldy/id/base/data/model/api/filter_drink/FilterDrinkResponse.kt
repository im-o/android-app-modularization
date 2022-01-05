package com.rivaldy.id.base.data.model.api.filter_drink


import com.google.gson.annotations.SerializedName

data class FilterDrinkResponse(
    @SerializedName("drinks")
    val drinks: List<FilterDrinkData>? = null
)