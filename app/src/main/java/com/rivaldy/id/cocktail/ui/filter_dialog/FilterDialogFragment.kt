package com.rivaldy.id.cocktail.ui.filter_dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rivaldy.id.base.base.BaseDialogFragment
import com.rivaldy.id.cocktail.databinding.FragmentFilterDialogBinding
import com.rivaldy.id.core.data.model.api.filter_drink.FilterDrinkData
import com.rivaldy.id.core.data.model.local.FilterDataLocal
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_ALCOHOLIC
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_CATEGORY
import com.rivaldy.id.core.utils.UtilConstants.FILTER_BY_GLASSES
import com.rivaldy.id.core.utils.UtilConstants.ZERO_DATA
import com.rivaldy.id.core.utils.UtilExceptions.handleApiError
import com.rivaldy.id.core.utils.UtilExtensions.isAreVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterDialogFragment : BaseDialogFragment<FragmentFilterDialogBinding>() {
    private val filterViewModel by viewModels<FilterViewModel>()
    private val filterAdapter by lazy { FilterAdapter { filterClick(it) } }
    private var extraFilterType: String? = null
    private lateinit var filterListener: FilterListener

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFilterDialogBinding
        get() = FragmentFilterDialogBinding::inflate

    override fun initView() {
        binding.listDataRV.adapter = filterAdapter
        initData()
        initClick()
    }

    override fun initObserver() {
        filterViewModel.filterDrinks.observe(this, {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showFilters(it.value.drinks)
                is DataResource.Failure -> showFailure(it)
            }
        })
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        activity?.handleApiError(failure)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        filterListener = context as FilterListener
    }

    private fun initData() {
        extraFilterType = arguments?.getString(EXTRA_FILTER_TYPE)
        when (extraFilterType) {
            FILTER_BY_CATEGORY -> filterViewModel.getFilterCategoriesApiCall()
            FILTER_BY_ALCOHOLIC -> filterViewModel.getFilterAlcoholicsApiCall()
            FILTER_BY_GLASSES -> filterViewModel.getFilterGlassesApiCall()
        }
    }

    private fun initClick() {
        binding.headerTV.setOnClickListener { dismiss() }
    }

    private fun showFilters(filters: List<FilterDrinkData>?) {
        showLoading(false)
        binding.noDataTV.isAreVisible(filters == null || filters.size == ZERO_DATA)
        val listFilter = mutableListOf<FilterDataLocal>()
        for (data in filters ?: return) {
            val name: String? = when (extraFilterType) {
                FILTER_BY_CATEGORY -> data.strCategory
                FILTER_BY_ALCOHOLIC -> data.strAlcoholic
                FILTER_BY_GLASSES -> data.strGlass
                else -> ""
            }
            listFilter.add(FilterDataLocal(extraFilterType, name))
        }
        filterAdapter.submitList(listFilter)
    }

    private fun filterClick(filter: FilterDataLocal) {
        filterListener.filterDrinkByName(filter)
        dismiss()
    }

    companion object {
        const val EXTRA_FILTER_TYPE = "EXTRA_FILTER_TYPE"
    }
}