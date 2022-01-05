package com.rivaldy.id.cocktail.ui

import androidx.activity.viewModels
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.model.api.category.DrinkData
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.UtilConstants.STR_COCKTAIL
import com.rivaldy.id.base.util.UtilExtensions.myToast
import com.rivaldy.id.base.util.UtilFunctions.loge
import com.rivaldy.id.cocktail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObservers() {
        // sample get list data
        viewModel.getCategoriesApiCall(STR_COCKTAIL)
        viewModel.categories.observe(this, {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showCategories(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    private fun showCategories(drinks: MutableList<DrinkData>?) {
        showLoading(false)
        loge("showCategories : $drinks")
    }

    override fun showFailure(failure: DataResource.Failure) {
        myToast("showFailure : $failure")
    }
}