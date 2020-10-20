package com.nikhil.storelistapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class StoreListResponse(
    val apps: List<App>
) {
    @Entity(tableName = "StoreDataTable")
    data class App(
        val currency: String,
        val data: Data,
        val money_format: String,
        val name: String,
        val cartKeyFk: Long

    ) {
        @PrimaryKey(autoGenerate = true)
        val mainKey: Int? = null

        data class Data(
            val add_to_cart: AddToCart,
            val downloads: Downloads,
            val sessions: Sessions,
            val total_sale: TotalSale
        ) {
            @Entity(tableName = "DataTable")
            data class AddToCart(
                @PrimaryKey val cartId: Long,
                val month_wise: MonthWise,
                val total: Int
            ) {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                )
            }

            data class Downloads(
                val month_wise: MonthWise,
                val total: Int
            ) {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                )
            }

            data class Sessions(
                val month_wise: MonthWise,
                val total: Int
            ) {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                )
            }

            data class TotalSale(
                val month_wise: MonthWise,
                val total: Int
            ) {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                )
            }
        }
    }
}