package com.rivaldy.id.test_feature.di

import android.content.Context
import com.rivaldy.id.core.di.SubModuleDependencies
import com.rivaldy.id.test_feature.ui.TestModuleActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/** Created by github.com/im-o on 9/24/2022. */

@Component(
    dependencies = [SubModuleDependencies::class],
    modules = [TestModuleViewModelModule::class]
)

@Singleton
interface TestModuleComponent {
    fun inject(activity: TestModuleActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(component: SubModuleDependencies): Builder
        fun build(): TestModuleComponent
    }
}