package com.rivaldy.id.core.data.model.api.detail_drink


import com.google.gson.annotations.SerializedName

data class DetailDrinkResponse(
    @SerializedName("drinks")
    val drinks: List<DetailDrinkData>? = null
)