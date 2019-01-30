package com.cbedoya.jsonplaceholder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PostsViewModelFactory @Inject constructor(
    private val postsListViewModel: PostListViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java!!)) {
            return postsListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
