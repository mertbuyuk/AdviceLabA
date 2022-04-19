package com.mb.advlab.api

import com.mb.advlab.model.LoginRequest
import com.mb.advlab.model.responses.BaseModel
import com.mb.advlab.model.SignupRequest
import com.mb.advlab.model.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AdvLabService {

    @POST("auth/process")
    suspend fun signRequest(@Body signupRequest: SignupRequest) : Response<BaseModel>

    @POST("auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest) : Response<LoginResponse>
}