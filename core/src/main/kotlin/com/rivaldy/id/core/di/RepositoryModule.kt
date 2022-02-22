package com.rivaldy.id.core.di

import com.rivaldy.id.core.data.AppDataManager
import com.rivaldy.id.core.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by rivaldy on 05/01/22.
 * Find me on my Github -> https://github.com/im-o
 */

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDataRepository(appDataManager: AppDataManager): DataRepository {
        return DataRepository(appDataManager)
    }
}