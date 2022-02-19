package com.rivaldy.id.cocktail.ui.graphql

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldy.id.base.base.BaseActivity
import com.rivaldy.id.base.data.network.DataResource
import com.rivaldy.id.base.util.UtilConstants
import com.rivaldy.id.base.util.UtilExtensions.isAreVisible
import com.rivaldy.id.cocktail.databinding.ActivitySampleQraphqlBinding
import com.rivaldy.id.core.CharacterListQuery
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleQraphqlActivity : BaseActivity<ActivitySampleQraphqlBinding>() {
    private val viewModel by viewModels<SampleGraphqlViewModel>()
    private val sampleGraphqlAdapter by lazy { SampleGraphqlAdapter { } }
    private var gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

    override fun getViewBinding() = ActivitySampleQraphqlBinding.inflate(layoutInflater)

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.listDataRV.layoutManager = gridLayoutManager
        binding.listDataRV.adapter = sampleGraphqlAdapter
    }

    override fun initObservers() {
        viewModel.getCharacterListQuery()
        viewModel.characters.observe(this) {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showCharacters(it.value.data)
                is DataResource.Failure -> showFailure(it)
            }
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
    }

    private fun showCharacters(data: CharacterListQuery.Data?) {
        showLoading(false)
        sampleGraphqlAdapter.submitList(data?.characters?.results)
        binding.noDataTV.isAreVisible(data?.characters?.results == null || data.characters?.results?.size == UtilConstants.ZERO_DATA)
    }
}