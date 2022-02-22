package com.rivaldy.id.core.data.remote.graphql

import com.apollographql.apollo3.api.ApolloResponse
import com.rivaldy.id.core.CharacterListQuery

/**
 * Created by rivaldy on 2/14/2022.
 * Find me on my Github -> https://github.com/im-o
 */

interface GraphqlHelper {
    suspend fun getCharacterListQuery(): ApolloResponse<CharacterListQuery.Data>
    suspend fun getCharacterListQueryByName(name: String): ApolloResponse<CharacterListQuery.Data>
}