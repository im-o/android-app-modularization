package com.rivaldy.id.cocktail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.base.data.DataRepository
import com.rivaldy.id.base.data.model.api.drink.DrinkResponse
import com.rivaldy.id.base.data.network.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _drinks: MutableLiveData<DataResource<DrinkResponse>> = MutableLiveData()
    val drinks: LiveData<DataResource<DrinkResponse>> = _drinks

    fun getCategoriesApiCall(category: String) = viewModelScope.launch {
        _drinks.value = DataResource.Loading
        _drinks.value = dataRepository.getCategoriesApiCall(category)
    }

    fun searchByNameApiCall(query: String) = viewModelScope.launch {
        _drinks.value = DataResource.Loading
        _drinks.value = dataRepository.searchByNameApiCall(query)
    }
}