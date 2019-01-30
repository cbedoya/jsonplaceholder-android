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
            val result = this
            result.postValue(Running(true))
            postService.allPosts().networkCall {
                onSuccess = {
                    result.postValue(Success(it))
                }

                onFailure = {
                    result.postValue(Failure(it))
                }
            }
        }
    }
}
