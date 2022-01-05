package com.rivaldy.id.base.data.remote

import com.rivaldy.id.base.data.model.api.category.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

interface ApiService {
    @GET("filter.php")
    suspend fun getCategories(@Query("c") category: String?): DrinkResponse
    suspend fun getAlcoholics(@Query("a") alcoholic: String?): DrinkResponse
}