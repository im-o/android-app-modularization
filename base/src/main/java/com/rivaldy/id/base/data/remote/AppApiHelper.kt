package com.rivaldy.id.base.data.remote

import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class AppApiHelper @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getCategoriesApiCall(category: String) = apiService.getCategories(category)
}