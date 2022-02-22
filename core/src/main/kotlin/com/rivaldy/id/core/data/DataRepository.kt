package com.rivaldy.id.core.data

import com.rivaldy.id.core.base.BaseRepository
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

    suspend fun getAlcoholicsApiCall(alcoholic: String) = safeApiCall {
        appDataManager.getAlcoholicsApiCall(alcoholic)
    }

    suspend fun getGlassesApiCall(glasses: String) = safeApiCall {
        appDataManager.getGlassesApiCall(glasses)
    }

    suspend fun getFilterCategoriesApiCall(query: String) = safeApiCall {
        appDataManager.getFilterCategoriesApiCall(query)
    }

    suspend fun getFilterAlcoholicsApiCall(query: String) = safeApiCall {
        appDataManager.getFilterAlcoholicsApiCall(query)
    }

    suspend fun getFilterGlassesApiCall(query: String) = safeApiCall {
        appDataManager.getFilterGlassesApiCall(query)
    }

    suspend fun searchByNameApiCall(query: String) = safeApiCall {
        appDataManager.searchByNameApiCall(query)
    }

    suspend fun getDetailByIdApiCall(id: String) = safeApiCall {
        appDataManager.getDetailByIdApiCall(id)
    }

    // Graphql API

    suspend fun getCharacterListQuery() = safeApiCall {
        appDataManager.getCharacterListQuery()
    }

    suspend fun getCharacterListQueryByName(name: String) = safeApiCall {
        appDataManager.getCharacterListQueryByName(name)
    }
}