package com.rivaldy.id.cocktail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.base.data.DataRepository
import com.rivaldy.id.base.data.model.api.category.DrinkResponse
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

    private val _categories: MutableLiveData<DataResource<DrinkResponse>> = MutableLiveData()
    val categories: LiveData<DataResource<DrinkResponse>> = _categories

    fun getCategoriesApiCall(category: String) = viewModelScope.launch {
        _categories.value = DataResource.Loading
        _categories.value = dataRepository.getCategoriesApiCall(category)
    }
}