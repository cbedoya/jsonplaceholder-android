package com.cbedoya.jsonplaceholder.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cbedoya.jsonplaceholder.model.CommentModel
import com.cbedoya.jsonplaceholder.networking.*
import javax.inject.Inject

class CommentListViewModel @Inject constructor(private val commentRepository: CommentRepository) : ViewModel() {

    val loader: MediatorLiveData<Boolean> = MediatorLiveData()
    val data: MutableLiveData<List<CommentModel>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = CONTROL_INVISIBLE }

    fun fetch(postId: Int) {
        loader.addSource(commentRepository.allComments(postId)) { result ->
            when (result) {
                is Running ->
                    progressBarVisibility.postValue(CONTROL_VISIBLE)
                is Success -> {
                    progressBarVisibility.postValue(CONTROL_INVISIBLE)
                    loader.postValue(true)
                    data.postValue(result.data)
                }
                is Failure -> {
                    progressBarVisibility.postValue(CONTROL_INVISIBLE)
                    loader.postValue(false)
                    error.postValue(result.error.localizedMessage)
                }
            }
        }
    }
}