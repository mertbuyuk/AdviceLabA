package com.mb.advlab.model.responses

data class ResponsePost(
    val message : String,
    val reason :String,
    val responseBody: List<PostResponse>
)

data class PostResponse (
    val id : Long,
    val type : FilmType,
    val filmName : String,
    val desc : String,
    val likeCount : Int,
    val unlikeCount : Int,
    val genre : String
        )

data class FilmType(
    val id : Long,
    val typeName : String
)
