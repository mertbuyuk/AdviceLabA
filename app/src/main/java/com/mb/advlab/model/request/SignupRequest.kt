package com.mb.advlab.model.request

data class SignupRequest(
    private val firstName : String,
    private val email : String,
    private val password : String
)