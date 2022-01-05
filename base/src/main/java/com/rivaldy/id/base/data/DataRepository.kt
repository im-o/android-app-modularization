package com.rivaldy.id.base.data

import com.rivaldy.id.base.base.BaseRepository
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class DataRepository @Inject constructor(
    private val appDataManager: AppDataManager
) : BaseRepository() {

    suspend fun getCategoriesApiCall(category: String) = safeApiCall {
        appDataManager.getCategoriesApiCall(category)
    }
}