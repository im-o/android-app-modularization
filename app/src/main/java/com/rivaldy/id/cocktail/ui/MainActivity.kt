package com.rivaldy.id.cocktail.ui

import android.os.Handler
import android.os.Looper
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.model.api.drink.DrinkData
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.PaginationListener
import com.rivaldy.id.base.util.UtilConstants.DEFAULT_LIMIT_PAGE
import com.rivaldy.id.base.util.UtilConstants.STR_COCKTAIL
import com.rivaldy.id.base.util.UtilConstants.ZERO_DATA
import com.rivaldy.id.base.util.UtilExceptions.handleApiError
import com.rivaldy.id.base.util.UtilExtensions.isAreVisible
import com.rivaldy.id.cocktail.R
import com.rivaldy.id.cocktail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), SearchView.OnQueryTextListener {
    private val mainAdapter = MainAdapter { mainAdapterClick(it) }
    private val viewModel by viewModels<MainViewModel>()
    private var listDrink = mutableListOf<DrinkData>()
    private var limitData = DEFAULT_LIMIT_PAGE
    private var counterLimitData = DEFAULT_LIMIT_PAGE
    private var isLastPage = false
    private var isLoadPage = false
    private var gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.listDataRV.layoutManager = gridLayoutManager
        binding.listDataRV.adapter = mainAdapter
        loadDrinkData("")
        initListener()
    }

    override fun initObservers() {
        viewModel.drinks.observe(this, {
            when (it) {
                is DataResource.Loading -> isLoadData(true)
                is DataResource.Success -> showCategories(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    override fun showFailure(failure: DataResource.Failure) {
        isLoadData(false)
        handleApiError(failure)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.searchMenu)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_by_cocktail_name)
        searchView.isIconified = true
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        loadDrinkData(query ?: "")
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val strQuery = newText ?: ""
        if (strQuery.isEmpty()) loadDrinkData(strQuery)
        return false
    }

    private fun loadDrinkData(query: String) {
        if (query.isEmpty()) viewModel.getCategoriesApiCall(STR_COCKTAIL)
        else {
            resetPage()
            viewModel.searchByNameApiCall(query)
        }
    }

    private fun initListener() {
        binding.swipeRefresh.setOnRefreshListener {
            resetPage()
            loadDrinkData("")
        }

        binding.listDataRV.addOnScrollListener(object : PaginationListener(gridLayoutManager) {
            override fun loadMoreItems() {
                isLoadData(true)
                limitData += counterLimitData
                Handler(Looper.getMainLooper()).postDelayed({ // sample for loading data because nothing API limit page
                    isLoadData(false)
                    showLimitData()
                }, 1500)
            }
            override fun isLastPage() = isLastPage
            override fun isLoading() = isLoadPage

        })
    }

    private fun resetPage() {
        isLastPage = false
        limitData = DEFAULT_LIMIT_PAGE
    }

    private fun isLoadData(isLoadData: Boolean) {
        isLoadPage = isLoadData
        binding.swipeRefresh.isRefreshing = isLoadData
    }

    private fun showCategories(drinks: MutableList<DrinkData>?) {
        isLoadData(false)
        binding.noDataTV.isAreVisible(drinks == null || drinks.size == ZERO_DATA)
        binding.listDataRV.isAreVisible(drinks != null || drinks?.size ?: 0 > ZERO_DATA)
        listDrink = drinks ?: return
        showLimitData()
    }

    private fun showLimitData() {
        val listData = if (listDrink.size >= limitData) listDrink.take(limitData) else listDrink
        isLastPage = listDrink.size <= limitData
        mainAdapter.submitList(listData)
    }

    private fun mainAdapterClick(drinkData: DrinkData) {

    }
}