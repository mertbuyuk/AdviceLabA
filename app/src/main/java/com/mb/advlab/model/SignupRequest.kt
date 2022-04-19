package com.mb.advlab.model

data class SignupRequest(
    private val firstName : String,
    private val email : String,
    private val password : String
)