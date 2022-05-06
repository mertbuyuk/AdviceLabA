package com.mb.advlab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.mb.advlab.R
import com.mb.advlab.databinding.ItemRelationsBinding
import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.model.responses.PostResponse

class RelationAdapters : ListAdapter<FolloweDDetails, RelationAdapters.RelationViewHolder>(DIFF_CALLBACK) {

    private var onClick : IOnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelationViewHolder {
        val binding = ItemRelationsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RelationViewHolder(binding)
    }

    class RelationViewHolder(private val itemBinding : ItemRelationsBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item : FolloweDDetails,click:IOnClick?){

            itemBinding.nameUser.text = item.name

            itemBinding.outlinedButton.setOnClickListener {
                if (item.status ==1){
                    item.status = 0
                    itemBinding.outlinedButton.text = "Takip et"
                }
                else{
                    item.status = 1
                    itemBinding.outlinedButton.text = "Takibi bırak"
                }
                click?.onClick(item)


            }
        }
    }

    override fun onBindViewHolder(holder: RelationViewHolder, position: Int) {
        holder.bind(getItem(position),onClick)
    }

    fun addListener(click: IOnClick?) {
        this.onClick = click
    }

    fun getPositionByItem(item: FolloweDDetails) = currentList.indexOf(item)

    fun followText(){

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