package com.nikhil.storelistapp.networkApis

import com.nikhil.storelistapp.entities.StoreListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface IStoreApi {

    @GET("dummy-app-data.json")
    fun getStore(): Single<StoreListResponse>
}