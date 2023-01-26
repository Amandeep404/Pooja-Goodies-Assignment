package com.example.assignment1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.assignment1.data.models.Item
import com.example.assignment1.data.searchModel.SearchItem
import com.example.assignment1.databinding.ItemVideoBinding
import com.example.assignment1.ui.fragments.ChannelContentFragment

class ChannelVideoItemAdapter(val clickListener:  (SearchItem) -> Unit): RecyclerView.Adapter<ChannelVideoItemAdapter.ChannelVideoViewHolder>() {

    inner class ChannelVideoViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(response : SearchItem, clickListener : (SearchItem) -> Unit){
            val thumbNailUrl = response.snippet.thumbnails.medium.url
            val channelLogo = response.snippet.thumbnails.medium.url
            val title = response.snippet.title
            val channelNameTitle = response.snippet.channelTitle
            val publishedAt = response.snippet.publishedAt

            binding.apply {
                videoThumbnail.load(thumbNailUrl)
                channelPicture.load(channelLogo)
                videoTitle.text = title
                channelName.text = channelNameTitle
                publishedTime.text = publishedAt

            }

            itemView.setOnClickListener {
                clickListener(response)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.snippet.title == newItem.snippet.title
        }
        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }
    val channelDiffer = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelVideoViewHolder {
        val layoutInflater = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ChannelVideoViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ChannelVideoViewHolder, position: Int) {
        holder.bind(channelDiffer.currentList[position], clickListener)
    }

    override fun getItemCount() = channelDiffer.currentList.size
}