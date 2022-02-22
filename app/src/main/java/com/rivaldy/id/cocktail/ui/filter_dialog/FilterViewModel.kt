package com.rivaldy.id.cocktail.ui.filter_dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.core.data.DataRepository
import com.rivaldy.id.core.data.model.api.filter_drink.FilterDrinkResponse
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.UtilConstants.STR_FILTER_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _filterDrinks: MutableLiveData<DataResource<FilterDrinkResponse>> = MutableLiveData()
    val filterDrinks: LiveData<DataResource<FilterDrinkResponse>> = _filterDrinks

    fun getFilterCategoriesApiCall() = viewModelScope.launch {
        _filterDrinks.value = DataResource.Loading
        _filterDrinks.value = dataRepository.getFilterCategoriesApiCall(STR_FILTER_LIST)
    }

    fun getFilterAlcoholicsApiCall() = viewModelScope.launch {
        _filterDrinks.value = DataResource.Loading
        _filterDrinks.value = dataRepository.getFilterAlcoholicsApiCall(STR_FILTER_LIST)
    }

    fun getFilterGlassesApiCall() = viewModelScope.launch {
        _filterDrinks.value = DataResource.Loading
        _filterDrinks.value = dataRepository.getFilterGlassesApiCall(STR_FILTER_LIST)
    }
}