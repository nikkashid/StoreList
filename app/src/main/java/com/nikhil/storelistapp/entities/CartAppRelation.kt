package com.nikhil.storelistapp.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CartAppRelation(
    @Embedded
    val addToCart: StoreListResponse.App.Data.AddToCart,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "cartKeyFk"
    )
    val app: StoreListResponse.App
)