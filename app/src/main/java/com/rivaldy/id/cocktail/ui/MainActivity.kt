package com.rivaldy.id.cocktail.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.cocktail.R
import com.rivaldy.id.cocktail.databinding.ActivityMainBinding
import com.rivaldy.id.cocktail.ui.drink_detail.DrinkDetailActivity
import com.rivaldy.id.cocktail.ui.filter_dialog.FilterDialogFragment
import com.rivaldy.id.cocktail.ui.filter_dialog.FilterListener
import com.rivaldy.id.cocktail.ui.graphql.SampleQraphqlActivity
import com.rivaldy.id.core.data.model.api.drink.DrinkData
import com.rivaldy.id.core.data.model.local.FilterDataLocal
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.PaginationListener
import com.rivaldy.id.core.utils.UtilConstants.DEFAULT_LIMIT_PAGE
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_ALCOHOLIC
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_CATEGORY
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_GLASSES
import com.rivaldy.id.core.utils.UtilConstants.STR_COCKTAIL
import com.rivaldy.id.core.utils.UtilConstants.ZERO_DATA
import com.rivaldy.id.core.utils.UtilExceptions.handleApiError
import com.rivaldy.id.core.utils.UtilExtensions.isAreVisible
import com.rivaldy.id.core.utils.UtilExtensions.myToast
import com.rivaldy.id.core.utils.UtilExtensions.openActivity
import com.rivaldy.id.core.utils.UtilFunctions.loge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), SearchView.OnQueryTextListener, FilterListener {
    private val mainAdapter by lazy { MainAdapter { mainAdapterClick(it) } }
    private val viewModel by viewModels<MainViewModel>()
    private var listDrink = mutableListOf<DrinkData>()
    private var limitData = DEFAULT_LIMIT_PAGE
    private var counterLimitData = DEFAULT_LIMIT_PAGE
    private var isLastPage = false
    private var isLoadPage = false
    private var gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

    private lateinit var manager: SplitInstallManager
    private var packageNameModule = ""
    private var dynamicModuleClassName = ""
    private var isModuleAdminInstalled = false

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.listDataRV.layoutManager = gridLayoutManager
        binding.listDataRV.adapter = mainAdapter
        loadDrinkData("")
        initListener()
        initSplitInstallManager()
    }

    override fun initObservers() {
        viewModel.drinks.observe(this) {
            when (it) {
                is DataResource.Loading -> isLoadData(true)
                is DataResource.Success -> showCategories(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sampleFeatureMenu -> openDialogInstallFeature()
            R.id.graphqlMenu -> openActivity(SampleQraphqlActivity::class.java)
            R.id.byCategoryMenu -> showFilterDialog(FILTER_BY_CATEGORY)
            R.id.byAlcoholicMenu -> showFilterDialog(FILTER_BY_ALCOHOLIC)
            R.id.byGlassMenu -> showFilterDialog(FILTER_BY_GLASSES)
        }
        return super.onOptionsItemSelected(item)
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

    override fun filterDrinkByName(filter: FilterDataLocal) {
        when (filter.type) {
            FILTER_BY_CATEGORY -> viewModel.getCategoriesApiCall(filter.name ?: "")
            FILTER_BY_ALCOHOLIC -> viewModel.getAlcoholicsApiCall(filter.name ?: "")
            FILTER_BY_GLASSES -> viewModel.getGlassesApiCall(filter.name ?: "")
        }
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

    private fun showCategories(drinks: MutableList<DrinkData>? = mutableListOf()) {
        isLoadData(false)
        binding.noDataTV.isAreVisible((drinks?.size ?: 0) == ZERO_DATA)
        binding.listDataRV.isAreVisible((drinks?.size ?: 0) > ZERO_DATA)
        listDrink = drinks ?: return
        showLimitData()
    }

    private fun showLimitData() {
        val listData = if (listDrink.size >= limitData) listDrink.take(limitData) else listDrink
        isLastPage = listDrink.size <= limitData
        mainAdapter.submitList(listData)
    }

    private fun mainAdapterClick(drinkData: DrinkData) {
        openActivity(DrinkDetailActivity::class.java) {
            putParcelable(DrinkDetailActivity.EXTRA_DRINK, drinkData)
        }
    }

    private fun showFilterDialog(filterType: String) {
        val filterDialogFragment = FilterDialogFragment()
        val bundle = Bundle()
        bundle.putString(FilterDialogFragment.EXTRA_FILTER_TYPE, filterType)
        filterDialogFragment.arguments = bundle
        filterDialogFragment.show(supportFragmentManager, FilterDialogFragment::class.java.simpleName)
    }

    // Init SplitInstallStateUpdatedListener
    private fun goToDynamicFeature() {
        dynamicModuleClassName = CLASS_ADMIN_FEATURE
        Intent().setClassName(packageNameModule, dynamicModuleClassName).also {
            startActivity(it)
        }
    }

    private fun initSplitInstallManager() {
        packageNameModule = packageName
        manager = SplitInstallManagerFactory.create(this)
        manager.installedModules.toList().forEach {
            if (it.equals(MODULE_ADMIN_FEATURE, true)) {
                isModuleAdminInstalled = true
            }
        }
    }

    private val splitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { state ->
        val name = state.moduleNames().joinToString(" - ")
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                toastLog("DOWNLOADING $name")
            }

            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                toastLog("REQUIRES_USER_CONFIRMATION")
            }

            SplitInstallSessionStatus.INSTALLED -> {
                toastLog("INSTALLED")
                initSplitInstallManager()
                isLoadData(false)
            }

            SplitInstallSessionStatus.INSTALLING -> {
                toastLog("INSTALLING $name")
            }

            SplitInstallSessionStatus.FAILED -> {
                toastLog("FAILED")
            }

            SplitInstallSessionStatus.CANCELING -> {
                toastLog("CANCELING")
            }

            SplitInstallSessionStatus.CANCELED -> {
                toastLog("CANCELED")
            }

            SplitInstallSessionStatus.DOWNLOADED -> {
                toastLog("DOWNLOADED")
            }

            SplitInstallSessionStatus.PENDING -> {
                toastLog("PENDING")
            }

            SplitInstallSessionStatus.UNKNOWN -> {
                toastLog("UNKNOWN")
            }
        }
    }

    private fun openDialogInstallFeature() {
        if (!isModuleAdminInstalled) {
            val builder = AlertDialog.Builder(this, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
                .setTitle(getString(R.string.install_feature))
                .setMessage(getString(R.string.want_to_install_feature))
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    isLoadData(true)
                    val request = SplitInstallRequest.newBuilder()
                        .addModule(MODULE_ADMIN_FEATURE)
                        .build()
                    manager.startInstall(request)
                        .addOnCompleteListener {
                            isLoadData(false)
                            toastLog("Success, try to open feature again.")
                        }
                        .addOnSuccessListener {
                            isLoadData(true)
                        }
                        .addOnFailureListener {
                            isLoadData(false)
                            toastLog("Error Installing new feature...")
                        }
                }.setNegativeButton(android.R.string.cancel, null)
            builder.create().show()
        } else goToDynamicFeature()
    }

    private fun toastLog(message: String) {
        loge(message)
        myToast(message)
        initSplitInstallManager()
    }

    override fun onResume() {
        manager.registerListener(splitInstallStateUpdatedListener)
        super.onResume()
    }

    override fun onPause() {
        manager.unregisterListener(splitInstallStateUpdatedListener)
        super.onPause()
    }

    companion object {
        const val MODULE_ADMIN_FEATURE = "test_feature"
        const val CLASS_ADMIN_FEATURE = "com.rivaldy.id.test_feature.ui.TestModuleActivity"
    }
}