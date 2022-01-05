package com.rivaldy.id.base.data.model.api.category


import com.google.gson.annotations.SerializedName

data class DrinkResponse(
    @SerializedName("drinks")
    val drinks: MutableList<DrinkData>? = null
)