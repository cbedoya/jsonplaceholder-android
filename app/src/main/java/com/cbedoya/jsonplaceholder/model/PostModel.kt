package com.cbedoya.jsonplaceholder.model

/**
 * Model class representing the following json structure
 *
 * {
 * "userId": Int,
 * "id": Int,
 * "title": String,
 * "body": String
 * }
 *
 */
data class PostModel(val userId: Int, val id: Int, val title: String, val body: String)
