package com.rivaldy.id.test_feature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivaldy.id.core.factory.ViewModelFactory
import com.rivaldy.id.test_feature.ui.TestModuleViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

/** Created by github.com/im-o on 9/24/2022. */

@Module
@InstallIn(ViewModelComponent::class)
abstract class TestModuleViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @TestModuleViewModelKey(TestModuleViewModel::class)
    abstract fun bindTestModuleViewModel(viewModel: TestModuleViewModel): ViewModel
}