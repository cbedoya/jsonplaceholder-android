package com.cbedoya.jsonplaceholder.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbedoya.jsonplaceholder.databinding.ItemCommentBinding
import com.cbedoya.jsonplaceholder.model.CommentModel
import com.cbedoya.jsonplaceholder.viewmodel.CommentViewModel

class CommentAdapter(private val comments: List<CommentModel>): RecyclerView.Adapter<CommentAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater)
        return PostViewHolder(binding)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(comments[position])

    inner class PostViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentModel) {
            val viewModel = CommentViewModel(comment.name, comment.email, comment.body)
            binding.comment = viewModel
            binding.executePendingBindings()
        }
    }
}