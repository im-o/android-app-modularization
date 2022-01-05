package com.rivaldy.id.cocktail.ui

import androidx.activity.viewModels
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.model.api.category.DrinkData
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.UtilConstants.STR_COCKTAIL
import com.rivaldy.id.base.util.UtilConstants.ZERO_DATA
import com.rivaldy.id.base.util.UtilExceptions.handleApiError
import com.rivaldy.id.base.util.UtilExtensions.isAreVisible
import com.rivaldy.id.cocktail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainAdapter = MainAdapter { mainAdapterClick(it) }
    private val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        binding.listDataRV.adapter = mainAdapter
        initData()
    }

    override fun initObservers() {
        viewModel.categories.observe(this, {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showCategories(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        handleApiError(failure)
    }

    private fun initData() {
        viewModel.getCategoriesApiCall(STR_COCKTAIL)
    }

    private fun showCategories(drinks: MutableList<DrinkData>?) {
        showLoading(false)
        binding.noDataTV.isAreVisible(drinks?.size == ZERO_DATA)
        mainAdapter.submitList(drinks)
    }

    private fun mainAdapterClick(drinkData: DrinkData) {

    }
}