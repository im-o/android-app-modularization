package com.rivaldy.id.core.di

import com.rivaldy.id.core.data.DataRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** Created by github.com/im-o on 9/24/2022. */

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SubModuleDependencies {
    fun provideDataRepository(): DataRepository
}