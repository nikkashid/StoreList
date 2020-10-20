package com.nikhil.storelistapp.database

import androidx.room.Database
import com.nikhil.storelistapp.entities.StoreListResponse

@Database(
    entities = [StoreListResponse.App::class],
    version = 1
)
abstract class AppDataBase {
}