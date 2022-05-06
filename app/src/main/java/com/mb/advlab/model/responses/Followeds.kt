package com.mb.advlab.model.responses

data class Followeds(
    val message : String,
    val reason :String,
    val responseBody: List<FolloweDDetails>
)

data class FolloweDDetails(
    val id : Long,
    val name : String,
    var status : Int = 1
)