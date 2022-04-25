package com.mb.advlab.model.responses

data class ResponsePost(
    val message : String,
    val reason :String,
    val responseBody: List<PostResponse>
)

data class PostResponse (
    val id : Long,
    val filmName : String
        )