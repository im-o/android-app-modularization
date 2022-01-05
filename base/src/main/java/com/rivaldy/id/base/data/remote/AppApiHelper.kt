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
    override suspend fun getAlcoholicsApiCall(alcoholic: String) = apiService.getAlcoholics(alcoholic)
    override suspend fun getGlassesApiCall(glasses: String) = apiService.getGlasses(glasses)
    override suspend fun getFilterCategoriesApiCall(query: String) = apiService.getFilterCategories(query)
    override suspend fun getFilterAlcoholicsApiCall(query: String) = apiService.getFilterAlcoholics(query)
    override suspend fun getFilterGlassesApiCall(query: String) = apiService.getFilterGlasses(query)
    override suspend fun searchByNameApiCall(query: String) = apiService.searchByName(query)
    override suspend fun getDetailByIdApiCall(id: String) = apiService.getDetailById(id)
}