package com.nikhil.storelistapp.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.storelistapp.entities.StoreListResponse
import com.nikhil.storelistapp.repository.StoreListRepository

class MainViewModel @ViewModelInject constructor(private val storeListRepository: StoreListRepository) :
    ViewModel() {

    fun getStoreList() {
        return storeListRepository.getStoreList()
    }

    fun observeResponse(): MutableLiveData<List<StoreListResponse.App>> {
        return storeListRepository.observeResponse()
    }
}