package com.nikhil.storelistapp.database

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.storelistapp.entities.StoreListResponse

@Dao
interface StoreDao {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(store: StoreListResponse.App): Single<Long>*/

    @Query("select * from StoresTable")
    fun getSortedData(): List<StoreListResponse.App>

}
