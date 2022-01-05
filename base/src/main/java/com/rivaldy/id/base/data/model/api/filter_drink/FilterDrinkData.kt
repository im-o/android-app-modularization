package com.rivaldy.id.base.data.model.api.filter_drink


import com.google.gson.annotations.SerializedName

data class FilterDrinkData(
    @SerializedName("strCategory")
    val strCategory: String? = null,
    @SerializedName("strAlcoholic")
    val strAlcoholic: String? = null,
    @SerializedName("strGlass")
    val strGlass: String? = null
)