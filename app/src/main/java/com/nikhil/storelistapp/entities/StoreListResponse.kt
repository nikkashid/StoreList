package com.nikhil.storelistapp.entities

data class StoreListResponse(
    val apps: List<App>
) {
    data class App(
        val currency: String,
        val data: Data,
        val money_format: String,
        val name: String
    ) {
        data class Data(
            val add_to_cart: AddToCart,
            val downloads: Downloads,
            val sessions: Sessions,
            val total_sale: TotalSale
        ) {
            data class AddToCart(
                val month_wise: MonthWise,
                val total: Long
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

    override fun toString(): String {
        return "StoreListResponse(apps=$apps)"
    }
}