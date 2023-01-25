package com.example.assignment1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment1.data.models.Item
import com.example.assignment1.databinding.ItemYtChannelListBinding

class HomeItemAdapter:RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    inner class HomeItemViewHolder(val binding: ItemYtChannelListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        val channelLogoUrl = item.snippet.thumbnails.medium.url
        val title = item.snippet.title

        holder.binding.apply {
             Glide.with(root)
                 .load(channelLogoUrl)
                 .centerCrop()
                 .into(ivChannelItem)
            tvChannelItem.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val layoutInflater = ItemYtChannelListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeItemViewHolder(layoutInflater)
    }

    override fun getItemCount() = differ.currentList.size


}