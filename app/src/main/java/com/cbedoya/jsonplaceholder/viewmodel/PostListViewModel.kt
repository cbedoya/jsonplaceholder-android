package com.cbedoya.jsonplaceholder.viewmodel

import androidx.lifecycle.ViewModel
import com.cbedoya.jsonplaceholder.networking.PostRepository
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

}
