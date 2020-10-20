package com.nikhil.storelistapp.database

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.storelistapp.entities.CartAppRelation

@Dao
interface StoreDao {

    @Query("select * from DataTable order by total ")
    fun getSortedData(sortOn : String) : List<CartAppRelation>

}
