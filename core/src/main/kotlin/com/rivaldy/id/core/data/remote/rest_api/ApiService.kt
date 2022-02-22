package com.rivaldy.id.core.data.remote.rest_api

import com.rivaldy.id.core.data.model.api.detail_drink.DetailDrinkResponse
import com.rivaldy.id.core.data.model.api.drink.DrinkResponse
import com.rivaldy.id.core.data.model.api.filter_drink.FilterDrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

interface ApiService {
    @GET("filter.php")
    suspend fun getCategories(@Query("c") category: String?): DrinkResponse

    @GET("filter.php")
    suspend fun getAlcoholics(@Query("a") alcoholic: String?): DrinkResponse

    @GET("filter.php")
    suspend fun getGlasses(@Query("g") glasses: String?): DrinkResponse

    @GET("list.php")
    suspend fun getFilterCategories(@Query("c") query: String?): FilterDrinkResponse

    @GET("list.php")
    suspend fun getFilterAlcoholics(@Query("a") query: String?): FilterDrinkResponse

    @GET("list.php")
    suspend fun getFilterGlasses(@Query("g") query: String?): FilterDrinkResponse

    @GET("search.php")
    suspend fun searchByName(@Query("s") query: String?): DrinkResponse

    @GET("lookup.php")
    suspend fun getDetailById(@Query("i") id: String?): DetailDrinkResponse
}