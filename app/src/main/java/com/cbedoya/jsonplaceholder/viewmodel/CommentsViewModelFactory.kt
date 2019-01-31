package com.cbedoya.jsonplaceholder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CommentsViewModelFactory @Inject constructor(
    private val commentsListViewModel: CommentListViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentListViewModel::class.java!!)) {
            return commentsListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
