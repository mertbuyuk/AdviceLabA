package com.mb.advlab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mb.advlab.databinding.ItemFilmBinding
import com.mb.advlab.model.responses.PostResponse

class PostAdapter : ListAdapter<PostResponse,PostAdapter.PostViewHolder>(DIFF_CALLBACK)  {
    var onClick : IOnClick? = null

    class PostViewHolder(private val binding : ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : PostResponse, onClick: IOnClick?){
            binding.postName.text = item.filmName

            binding.postLayout.setOnClickListener {
                onClick?.onClick(item)
            }
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostResponse>() {
            override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse) =
                oldItem.filmName == newItem.filmName

            override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position),onClick)
    }

    fun addListener(click: IOnClick?) {
        this.onClick = click
    }
}