package com.nikhil.storelistapp.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nikhil.storelistapp.entities.StoreListResponse
import com.nikhil.storelistapp.networkApis.IStoreApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class StoreListRepository @Inject constructor(
    private val iStoreApi: IStoreApi
) {

    private val listResponse: MutableLiveData<List<StoreListResponse.App>> = MutableLiveData()

    fun getStoreList() {

        iStoreApi.getStore().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<StoreListResponse>() {
                override fun onSuccess(storeResponse: StoreListResponse) {
                    Log.d(Companion.TAG, "onSuccess: ${storeResponse.apps[0].data.downloads.total}")
                    storeResponse(storeResponse)
                }

                override fun onError(e: Throwable) {
                    Log.d(Companion.TAG, "onError: ${e.printStackTrace()}")
                }
            })
    }

    private fun storeResponse(storeResponse: StoreListResponse) {

        val app = storeResponse.apps
        listResponse.value = app

    }

    fun observeResponse(): MutableLiveData<List<StoreListResponse.App>> {
        return listResponse
    }

    companion object {
        private const val TAG = "StoreListRepository"
    }

}