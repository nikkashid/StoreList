package com.nikhil.storelistapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.storelistapp.R
import com.nikhil.storelistapp.databinding.StoreItemLayoutBinding
import com.nikhil.storelistapp.entities.StoreListResponse

class StoreAdapter(iClickListener: IClickListener) :
    ListAdapter<StoreListResponse.App, StoreAdapter.StoreViewHolder>(differCallback) {

    var iClickListener: IClickListener = iClickListener

    inner class StoreViewHolder(itemView: StoreItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        var binding: StoreItemLayoutBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val weatherItemBinding: StoreItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.store_item_layout, parent, false
        )

        return StoreViewHolder(weatherItemBinding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val storeEntity = getItem(position)
        holder.binding.storeAdapterItem = storeEntity

        holder.binding.tvViewDetails.setOnClickListener {
            iClickListener.onClickListener(storeEntity)
        }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<StoreListResponse.App>() {

            override fun areItemsTheSame(
                oldItem: StoreListResponse.App,
                newItem: StoreListResponse.App
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: StoreListResponse.App,
                newItem: StoreListResponse.App
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface IClickListener {
        fun onClickListener(storeItem: StoreListResponse.App)
    }
}