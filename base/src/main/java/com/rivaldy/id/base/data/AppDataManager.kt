package com.rivaldy.id.base.data

import com.apollographql.apollo3.api.ApolloResponse
import com.rivaldy.id.base.data.model.api.detail_drink.DetailDrinkResponse
import com.rivaldy.id.base.data.model.api.drink.DrinkResponse
import com.rivaldy.id.base.data.model.api.filter_drink.FilterDrinkResponse
import com.rivaldy.id.base.data.remote.AppApiHelper
import com.rivaldy.id.base.data.remote.graphql.AppGraphqlHelper
import com.rivaldy.id.core.CharacterListQuery
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

class AppDataManager @Inject constructor(
    private val api: AppApiHelper,
    private val graphql: AppGraphqlHelper
) : DataManager {
    override suspend fun getCategoriesApiCall(category: String): DrinkResponse {
        return api.getCategoriesApiCall(category)
    }

    override suspend fun getAlcoholicsApiCall(alcoholic: String): DrinkResponse {
        return api.getAlcoholicsApiCall(alcoholic)
    }

    override suspend fun getGlassesApiCall(glasses: String): DrinkResponse {
        return api.getGlassesApiCall(glasses)
    }

    override suspend fun getFilterCategoriesApiCall(query: String): FilterDrinkResponse {
        return api.getFilterCategoriesApiCall(query)
    }

    override suspend fun getFilterAlcoholicsApiCall(query: String): FilterDrinkResponse {
        return api.getFilterAlcoholicsApiCall(query)
    }

    override suspend fun getFilterGlassesApiCall(query: String): FilterDrinkResponse {
        return api.getFilterGlassesApiCall(query)
    }

    override suspend fun searchByNameApiCall(query: String): DrinkResponse {
        return api.searchByNameApiCall(query)
    }

    override suspend fun getDetailByIdApiCall(id: String): DetailDrinkResponse {
        return api.getDetailByIdApiCall(id)
    }

    override suspend fun getCharacterListQuery(): ApolloResponse<CharacterListQuery.Data> {
        return graphql.getCharacterListQuery()
    }

    override suspend fun getCharacterListQueryByName(name: String): ApolloResponse<CharacterListQuery.Data> {
        return graphql.getCharacterListQueryByName(name)
    }
}