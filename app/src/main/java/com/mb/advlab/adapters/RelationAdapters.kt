package com.mb.advlab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mb.advlab.R
import com.mb.advlab.databinding.ItemRelationsBinding
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.model.responses.PostResponse
import com.mb.advlab.utils.ImageHelper
import kotlin.concurrent.fixedRateTimer

class RelationAdapters : ListAdapter<FolloweDDetails, RelationAdapters.RelationViewHolder>(DIFF_CALLBACK) {
    var flag : Int = 0

    private var onClick : IOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelationViewHolder {
        val binding = ItemRelationsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RelationViewHolder(binding)
    }

    class RelationViewHolder(private val itemBinding : ItemRelationsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val imageHelper = ImageHelper()
        val btn = itemBinding.outlinedButton

        fun bind(item : FolloweDDetails,click:IOnClick?){

            itemBinding.nameUser.text = item.name

            if(item.photo != null){
                val img = imageHelper.stringToBitmap(item.photo)
                Glide.with(itemBinding.root.context)
                    .asBitmap()
                    .load(img)
                    .centerInside()
                    .into(itemBinding.appCompatImageView2)
            }

            itemBinding.outlinedButton.setOnClickListener {
                if (item.status ==0){
                    item.status = 1
                    itemBinding.outlinedButton.text = "Takip et"
                }
                else if (item.status == 1){
                    item.status = 0
                    itemBinding.outlinedButton.text = "Takip ediliyor"
                }
                click?.onClick(item)
            }
        }
    }

    override fun onBindViewHolder(holder: RelationViewHolder, position: Int) {
        holder.bind(getItem(position),onClick)

        if (flag == 1){
            holder.btn.visibility = View.GONE
        }
    }

    fun addListener(click: IOnClick?) {
        this.onClick = click
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