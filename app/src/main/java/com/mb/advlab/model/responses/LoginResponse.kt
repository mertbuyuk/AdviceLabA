package com.mb.advlab.model.responses

data class LoginResponse(
    val message : String,
    val reason :String,
    val responseBody: ResponseBody
)

data class ResponseBody(
    val id : Long,
    val firstName : String,
    val email : String,
    val jwt : String,
    var photo : String
)