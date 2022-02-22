package com.rivaldy.id.core.data.model.api.drink


import com.google.gson.annotations.SerializedName

data class DrinkResponse(
    @SerializedName("drinks")
    val drinks: MutableList<DrinkData>? = null
)