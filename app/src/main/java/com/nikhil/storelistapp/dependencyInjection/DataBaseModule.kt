package com.nikhil.storelistapp.dependencyInjection

/*import android.content.Context
import androidx.room.Room
import com.nikhil.storelistapp.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "STORE_LIST_DB"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDataBase) = db.getStoreDao()

}*/
