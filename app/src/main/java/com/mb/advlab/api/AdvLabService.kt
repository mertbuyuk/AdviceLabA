package com.mb.advlab.api

import com.mb.advlab.model.request.LoginRequest
import com.mb.advlab.model.request.SharePost
import com.mb.advlab.model.request.SignupRequest
import com.mb.advlab.model.responses.*
import dagger.hilt.internal.GeneratedEntryPoint
import retrofit2.Response
import retrofit2.http.*

interface AdvLabService {

    @POST("auth/process")
    suspend fun signRequest(@Body signupRequest: SignupRequest) : Response<BaseModel>

    @POST("auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("user/addPosttoUser/{id}")
    suspend fun sharePost(@Header("Authorization") token : String, @Path("id") id : Long, @Body sharePostRequest : SharePost) :Response<LoginResponse> // post response ile değişicek

    @GET("user/getFollowedList/{id}")
    suspend fun getFollowedList(@Header("Authorization") token : String, @Path("id") id : Long) : Response<Followeds>

    @GET("user/getFollowerList")
    suspend fun getFollowerList(@Header("Authorization") token : String, @Path("id") id : Long) : Response<Followers>

    @GET("user/getCount/{id}")
    suspend fun getCount(@Header("Authorization") token : String, @Path("id") id : Long) : Response<GetCount>

    @GET("user/getPostById/{id}")
    suspend fun getUsersPost(@Header("Authorization") token : String, @Path("id") id : Long) : Response<ResponsePost>

}