package com.rivaldy.id.test_feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.rivaldy.id.core.CharacterListQuery
import com.rivaldy.id.core.data.DataRepository
import com.rivaldy.id.core.data.network.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 2/14/2022.
 * Find me on my Github -> https://github.com/im-o
 */

@HiltViewModel
class TestModuleViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _characters: MutableLiveData<DataResource<ApolloResponse<CharacterListQuery.Data>>> = MutableLiveData()
    val characters: LiveData<DataResource<ApolloResponse<CharacterListQuery.Data>>> = _characters

    fun getCharacterListQuery() = viewModelScope.launch {
        _characters.value = DataResource.Loading
        _characters.value = dataRepository.getCharacterListQuery()
    }

    fun getCharacterListQueryByName(name: String) = viewModelScope.launch {
        _characters.value = DataResource.Loading
        _characters.value = dataRepository.getCharacterListQueryByName(name)
    }
}