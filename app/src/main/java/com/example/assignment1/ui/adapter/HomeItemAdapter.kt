package com.example.assignment1.ui.adapter

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment1.R
import com.example.assignment1.data.models.Item
import com.example.assignment1.databinding.ItemYtChannelListBinding
import com.example.assignment1.ui.fragments.HomeFragment

class HomeItemAdapter:RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.snippet == newItem.snippet
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    inner class HomeItemViewHolder(val binding: ItemYtChannelListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(response : Item){

            val channelLogoUrl = response.snippet.thumbnails.medium.url
            val title = response.snippet.title

            binding.apply {
                Glide.with(root)
                    .load(channelLogoUrl)
                    .centerCrop()
                    .into(ivChannelItem)
                tvChannelItem.text = title

            }
            itemView.setOnClickListener {
                onItemClickListener?.let { item ->
                    item(response)

                }
            }

        }
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(differ.currentList[position] )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val layoutInflater = ItemYtChannelListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeItemViewHolder(layoutInflater)
    }

    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((Item) -> Unit)? = null

    fun setOnItemClickListener(listener : (Item) -> Unit){
        onItemClickListener = listener
    }


}