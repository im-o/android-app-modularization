package com.rivaldy.id.test_feature.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.apollographql.apollo3.api.ApolloResponse
import com.rivaldy.id.base.modular.BaseModuleActivity
import com.rivaldy.id.core.di.SubModuleDependencies
import com.rivaldy.id.core.CharacterListQuery
import com.rivaldy.id.core.data.network.DataResource
import com.rivaldy.id.core.utils.UtilExceptions.handleApiError
import com.rivaldy.id.test_feature.databinding.ActivityTestModuleBinding
import com.rivaldy.id.test_feature.di.DaggerTestModuleComponent
import com.rivaldy.id.test_feature.di.TestModuleComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TestModuleActivity : BaseModuleActivity<ActivityTestModuleBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TestModuleViewModel by viewModels { viewModelFactory }
    private var component: TestModuleComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestModuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }


    override fun initInjectComponent() {
        getActivityComponent()?.inject(this)
    }

    override fun getViewBinding() = ActivityTestModuleBinding.inflate(layoutInflater)

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
    }

    override fun initObservers() {
        viewModel.characters.observe(this) {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showCharacters(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        }
    }

    private fun showCharacters(response: ApolloResponse<CharacterListQuery.Data>) {
        var strName = ""
        for (item in response.data?.characters?.results ?: return) strName += "${item?.name}\n"
        binding.nameTV.text = strName
        showLoading(false)
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        handleApiError(failure)
    }

    private fun initData() {
        viewModel.getCharacterListQuery()
    }

    private fun getActivityComponent(): TestModuleComponent? {
        if (component == null) {
            component = DaggerTestModuleComponent.builder()
                .context(applicationContext)
                .dependencies(
                    EntryPointAccessors.fromApplication(
                        applicationContext,
                        SubModuleDependencies::class.java
                    )
                )
                .build()
        }
        return component
    }
}