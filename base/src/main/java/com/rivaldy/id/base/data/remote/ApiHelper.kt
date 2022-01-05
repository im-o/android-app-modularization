package com.rivaldy.id.base.data.remote

import com.rivaldy.id.base.data.model.api.category.DrinkResponse

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

interface ApiHelper {
    suspend fun getCategoriesApiCall(category: String): DrinkResponse
}