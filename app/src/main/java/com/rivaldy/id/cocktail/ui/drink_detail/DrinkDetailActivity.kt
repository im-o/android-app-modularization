package com.rivaldy.id.cocktail.ui.drink_detail

import android.os.Build
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.cocktail.R
import com.rivaldy.id.cocktail.databinding.ActivityDrinkDetailBinding
import com.rivaldy.id.core.data.model.api.detail_drink.DetailDrinkData
import com.rivaldy.id.core.data.model.api.drink.DrinkData
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.UtilExceptions.handleApiError
import com.rivaldy.id.core.utils.UtilExtensions.parcelableExtra
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailActivity : BaseActivity<ActivityDrinkDetailBinding>() {
    private val drinkDetailViewModel by viewModels<DrinkDetailViewModel>()
    private var extraDrink: DrinkData? = null

    override fun getViewBinding() = ActivityDrinkDetailBinding.inflate(layoutInflater)

    override fun initView() {
        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        initData()
    }

    private fun initData() {
        extraDrink = intent.parcelableExtra(EXTRA_DRINK)
        if (extraDrink != null) drinkDetailViewModel.getDetailByIdApiCall(extraDrink?.idDrink ?: "")
    }

    override fun initObservers() {
        drinkDetailViewModel.drinkDetail.observe(this) {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showDetail(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        }
    }

    private fun showDetail(drinks: List<DetailDrinkData>?) {
        showLoading(false)
        if (drinks != null && drinks.isNotEmpty()) {
            val drink = drinks[0] // because response lookup by id is list
            Glide.with(binding.root.context)
                .load(drink.strDrinkThumb)
                .placeholder(R.color.colorDividerHigh)
                .into(binding.drinkImageIV)
            binding.drinkTitleTV.text = drink.strDrink
            binding.drinkInfoTV.text = getString(R.string.str_info, drink.strCategory, drink.strAlcoholic, drink.strGlass)
            binding.descriptionTV.text = drink.strInstructions
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        handleApiError(failure)
    }

    companion object {
        const val EXTRA_DRINK = "EXTRA_DRINK"
    }
}
