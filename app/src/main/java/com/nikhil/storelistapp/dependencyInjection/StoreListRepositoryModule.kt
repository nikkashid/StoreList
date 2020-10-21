package com.nikhil.storelistapp.dependencyInjection

import com.nikhil.storelistapp.networkApis.IStoreApi
import com.nikhil.storelistapp.repository.StoreListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StoreListRepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        iStoreApi: IStoreApi/*,
        storeListDao: StoreDao*/
    ): StoreListRepository {
        return StoreListRepository(iStoreApi/*, storeListDao*/)
    }

}