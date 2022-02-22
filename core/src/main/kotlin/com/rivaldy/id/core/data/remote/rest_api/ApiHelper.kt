package com.rivaldy.id.core.data.remote.rest_api

import com.rivaldy.id.core.data.model.api.detail_drink.DetailDrinkResponse
import com.rivaldy.id.core.data.model.api.drink.DrinkResponse
import com.rivaldy.id.core.data.model.api.filter_drink.FilterDrinkResponse

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

interface ApiHelper {
    suspend fun getCategoriesApiCall(category: String): DrinkResponse
    suspend fun getAlcoholicsApiCall(alcoholic: String): DrinkResponse
    suspend fun getGlassesApiCall(glasses: String): DrinkResponse
    suspend fun getFilterCategoriesApiCall(query: String): FilterDrinkResponse
    suspend fun getFilterAlcoholicsApiCall(query: String): FilterDrinkResponse
    suspend fun getFilterGlassesApiCall(query: String): FilterDrinkResponse
    suspend fun searchByNameApiCall(query: String): DrinkResponse
    suspend fun getDetailByIdApiCall(id: String): DetailDrinkResponse
}