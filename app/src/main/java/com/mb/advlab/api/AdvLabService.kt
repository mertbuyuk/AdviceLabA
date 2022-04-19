package com.mb.advlab.api

import com.mb.advlab.model.BaseModel
import com.mb.advlab.model.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AdvLabService {

    @POST("auth/process")
    suspend fun signRequest(@Body signupRequest: SignupRequest) : Response<BaseModel>
}