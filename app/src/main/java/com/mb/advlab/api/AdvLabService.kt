package com.mb.advlab.api

import com.mb.advlab.model.LoginRequest
import com.mb.advlab.model.SignupRequest
import com.mb.advlab.model.responses.*
import retrofit2.Response
import retrofit2.http.*

interface AdvLabService {

    @POST("auth/process")
    suspend fun signRequest(@Body signupRequest: SignupRequest) : Response<BaseModel>

    @POST("auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @GET("user/getFollowedList/{id}")
    suspend fun getFollowedList(@Header("Authorization") token : String, @Path("id") id : Long) : Response<Followeds>

    @GET("user/getFollowerList")
    suspend fun getFollowerList(@Header("Authorization") token : String, @Path("id") id : Long) : Response<Followers>


    @GET("user/getCount/{id}")
    suspend fun getCount(@Header("Authorization") token : String, @Path("id") id : Long) : Response<GetCount>

}