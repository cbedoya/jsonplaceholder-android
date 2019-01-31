package com.cbedoya.jsonplaceholder.networking

import com.cbedoya.jsonplaceholder.model.CommentModel
import com.cbedoya.jsonplaceholder.model.PostModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("/posts")
    fun allPosts(): Deferred<Response<List<PostModel>>>

    @GET("/posts/{id}/comments")
    fun allComments(@Path("id") postId: Int): Deferred<Response<List<CommentModel>>>
}
