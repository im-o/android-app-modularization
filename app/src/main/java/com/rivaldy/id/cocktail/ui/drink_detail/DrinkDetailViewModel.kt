package com.rivaldy.id.cocktail.ui.drink_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.base.data.DataRepository
import com.rivaldy.id.base.data.model.api.detail_drink.DetailDrinkResponse
import com.rivaldy.id.base.data.network.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _drinkDetail: MutableLiveData<DataResource<DetailDrinkResponse>> = MutableLiveData()
    val drinkDetail: LiveData<DataResource<DetailDrinkResponse>> = _drinkDetail

    fun getDetailByIdApiCall(id: String) = viewModelScope.launch {
        _drinkDetail.value = DataResource.Loading
        _drinkDetail.value = dataRepository.getDetailByIdApiCall(id)
    }
}