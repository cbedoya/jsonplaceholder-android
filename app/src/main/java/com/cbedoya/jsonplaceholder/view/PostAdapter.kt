package com.cbedoya.jsonplaceholder.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbedoya.jsonplaceholder.databinding.ItemPostBinding
import com.cbedoya.jsonplaceholder.model.PostModel
import com.cbedoya.jsonplaceholder.viewmodel.PostViewModel

class PostAdapter(private val posts: List<PostModel>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater)
        return PostViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(posts[position])

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostModel) {
            binding.post = PostViewModel(post.title, post.body)
            binding.executePendingBindings()
        }
    }
}