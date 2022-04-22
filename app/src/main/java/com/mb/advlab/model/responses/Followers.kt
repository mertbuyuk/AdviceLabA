package com.mb.advlab.model.responses

data class Followers(
    val message : String,
    val reason :String,
    val responseBody: List<FollowerDetails>
)

data class FollowerDetails(
    val id : Long,
    val name : String
)