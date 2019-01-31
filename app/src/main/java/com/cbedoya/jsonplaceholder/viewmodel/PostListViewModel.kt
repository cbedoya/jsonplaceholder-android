package com.cbedoya.jsonplaceholder.viewmodel

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cbedoya.jsonplaceholder.model.PostModel
import com.cbedoya.jsonplaceholder.networking.Failure
import com.cbedoya.jsonplaceholder.networking.PostRepository
import com.cbedoya.jsonplaceholder.networking.Running
import com.cbedoya.jsonplaceholder.networking.Success
import javax.inject.Inject

internal const val CONTROL_VISIBLE = View.VISIBLE
internal const val CONTROL_INVISIBLE = View.GONE

class PostListViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val loader: MediatorLiveData<Boolean> = MediatorLiveData()
    val data: MutableLiveData<List<PostModel>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = CONTROL_INVISIBLE }

    init {
        fetch()
    }

    private fun fetch() {
        loader.addSource(postRepository.allPosts()) { result ->
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
