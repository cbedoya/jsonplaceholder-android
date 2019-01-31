package com.cbedoya.jsonplaceholder.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cbedoya.jsonplaceholder.extensions.networkCall
import com.cbedoya.jsonplaceholder.model.CommentModel
import javax.inject.Inject

class CommentRepository @Inject constructor(val postService: PostService)  {
    private val allComments: MutableLiveData<List<CommentModel>?> = MutableLiveData()

    fun allComments(postId: Int): LiveData<NetworkResult<List<CommentModel>>> {
        return MutableLiveData<NetworkResult<List<CommentModel>>>().apply {
            postValue(Running())
            postService.allComments(postId).networkCall {
                onSuccess = {
                    postValue(Success(it))
                    setComments(it)
                }

                onFailure = {
                    postValue(Failure(it))
                    setComments(null)
                }
            }
        }
    }

    private fun setComments(posts: List<CommentModel>?) {
        allComments.value = posts
    }
}
