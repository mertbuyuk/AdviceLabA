package com.mb.advlab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.mb.advlab.databinding.ItemRelationsBinding
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.model.responses.PostResponse

class RelationAdapters : ListAdapter<FolloweDDetails, RelationAdapters.RelationViewHolder>(DIFF_CALLBACK) {

    class RelationViewHolder(private val itemBinding : ItemRelationsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item : FolloweDDetails){

            itemBinding.nameUser = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelationViewHolder {
        val binding = ItemRelationsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RelationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FolloweDDetails>() {
            override fun areItemsTheSame(oldItem: FolloweDDetails, newItem: FolloweDDetails) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FolloweDDetails, newItem: FolloweDDetails) =
                oldItem == newItem
        }
    }

}