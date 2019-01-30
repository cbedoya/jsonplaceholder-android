package com.cbedoya.jsonplaceholder.networking

import com.cbedoya.jsonplaceholder.model.PostModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    fun allPosts(): Deferred<Response<List<PostModel>>>
}
