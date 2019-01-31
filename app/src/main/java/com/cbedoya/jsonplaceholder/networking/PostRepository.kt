package com.cbedoya.jsonplaceholder.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cbedoya.jsonplaceholder.extensions.networkCall
import com.cbedoya.jsonplaceholder.model.PostModel
import javax.inject.Inject

class PostRepository @Inject constructor(val postService: PostService)  {
    private val allPosts: MutableLiveData<List<PostModel>?> = MutableLiveData()

    fun allPosts(): LiveData<NetworkResult<List<PostModel>>> {
        return MutableLiveData<NetworkResult<List<PostModel>>>().apply {
            postValue(Running())
            postService.allPosts().networkCall {
                onSuccess = {
                    postValue(Success(it))
                    setPosts(it)
                }

                onFailure = {
                    postValue(Failure(it))
                    setPosts(null)
                }
            }
        }
    }

    private fun setPosts(posts: List<PostModel>?) {
        allPosts.value = posts
    }
}
