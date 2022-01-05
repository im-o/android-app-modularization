package com.rivaldy.id.base.di

import com.rivaldy.id.base.data.AppDataManager
import com.rivaldy.id.base.data.DataRepository
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