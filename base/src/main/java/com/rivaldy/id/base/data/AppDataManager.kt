package com.rivaldy.id.base.data

import com.rivaldy.id.base.data.model.api.drink.DrinkResponse
import com.rivaldy.id.base.data.remote.AppApiHelper
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class AppDataManager @Inject constructor(
    private val api: AppApiHelper
) : DataManager {
    override suspend fun getCategoriesApiCall(category: String): DrinkResponse {
        return api.getCategoriesApiCall(category)
    }
}