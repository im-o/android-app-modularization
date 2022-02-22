package com.rivaldy.id.core.data.remote.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.rivaldy.id.core.CharacterListQuery
import javax.inject.Inject

/**
 * Created by rivaldy on 2/14/2022.
 * Find me on my Github -> https://github.com/im-o
 */

class AppGraphqlHelper @Inject constructor(
    private val apolloClient: ApolloClient
) : GraphqlHelper {
    override suspend fun getCharacterListQuery(): ApolloResponse<CharacterListQuery.Data> {
        return apolloClient.query(CharacterListQuery()).execute()
    }

    override suspend fun getCharacterListQueryByName(name: String): ApolloResponse<CharacterListQuery.Data> {
        return apolloClient.query(CharacterListQuery(name = Optional.presentIfNotNull(name))).execute()
    }
}