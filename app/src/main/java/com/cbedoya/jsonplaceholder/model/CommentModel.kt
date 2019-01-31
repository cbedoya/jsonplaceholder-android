package com.cbedoya.jsonplaceholder.model

/**
 * Model class representing the following json structure
 *
 * "postId": Int,
 * "id": Int,
 * "name": String",
 * "email": String,
 * "body": String
 */
data class CommentModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)