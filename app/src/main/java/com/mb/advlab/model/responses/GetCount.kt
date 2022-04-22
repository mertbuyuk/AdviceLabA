package com.mb.advlab.model.responses

data class GetCount(
    val message : String,
    val reason :String,
    val responseBody: CountBody
)

data class CountBody(
    val follower : Long,
    val following : Long
)

