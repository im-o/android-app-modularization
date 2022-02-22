package com.rivaldy.id.core.data.model.api.detail_drink


import com.google.gson.annotations.SerializedName

data class DetailDrinkData(
    @SerializedName("dateModified")
    val dateModified: String? = null,
    @SerializedName("idDrink")
    val idDrink: String? = null,
    @SerializedName("strAlcoholic")
    val strAlcoholic: String? = null,
    @SerializedName("strCategory")
    val strCategory: String? = null,
    @SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? = null,
    @SerializedName("strDrink")
    val strDrink: String? = null,
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: Any? = null,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = null,
    @SerializedName("strGlass")
    val strGlass: String? = null,
    @SerializedName("strIBA")
    val strIBA: String? = null,
    @SerializedName("strImageAttribution")
    val strImageAttribution: String? = null,
    @SerializedName("strImageSource")
    val strImageSource: String? = null,
    @SerializedName("strInstructions")
    val strInstructions: String? = null,
    @SerializedName("strTags")
    val strTags: String? = null,
    @SerializedName("strVideo")
    val strVideo: Any? = null
)