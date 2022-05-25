package com.mb.advlab.api

import com.mb.advlab.model.request.*
import com.mb.advlab.model.responses.*
import dagger.hilt.internal.GeneratedEntryPoint
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AdvLabService {

    @POST("auth/process")
    suspend fun signRequest(@Body signupRequest: SignupRequest) : Response<BaseModel>

    @POST("auth/login")
    suspend fun loginRequest(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("user/addPosttoUser/{id}")
    suspend fun sharePost(@Header("Authorization") token : String, @Path("id") id : Long, @Body sharePostRequest : SharePost) :Response<LoginResponse> // post response ile değişicek

    @POST("user/followById")
    suspend fun followById(@Header("Authorization") token : String, @Body fromTo : FollowUnRequest) : Response<BaseModel>

    @POST("user/deleteById")
    suspend fun deleteById(@Header("Authorization") token : String, @Body fromTo : FollowUnRequest) : Response<BaseModel>

    @Multipart
    @POST("user/savephoto/{id}")
    suspend fun saveUserPhoto(@Header("Authorization") token : String, @Path("id") id : Long, @Part file :  MultipartBody.Part) : Response<BaseModel>

    @GET("user/getphoto/{id}")
    suspend fun getUserPhoto(@Header("Authorization") token : String, @Path("id") id : Long) : Response<BaseModel>

    @GET("findById/{id}")
    suspend fun findById(@Header("Authorization") token : String, @Path("id") id : Long) : Response<LoginResponse>

    @GET("user/getFollowedList/{id}")
    suspend fun getFollowedList(@Header("Authorization") token : String, @Path("id") id : Long) : Response<Followeds>

    @GET("user/getFollowerList/{id}")
    suspend fun getFollowerList(@Header("Authorization") token : String, @Path("id") id : Long): Response<Followeds>

    @GET("user/getCount/{id}")
    suspend fun getCount(@Header("Authorization") token : String, @Path("id") id : Long) : Response<GetCount>

    @GET("user/getPostById/{id}")
    suspend fun getUsersPost(@Header("Authorization") token : String, @Path("id") id : Long) : Response<ResponsePost>

    @GET("user/searchUsers")
    suspend fun searchUser(@Header("Authorization") token : String, @Query("keyword") keyword : String) : Response<Followeds>

    @GET("post/getPostById/{id}")
    suspend fun getPostDetails(@Header("Authorization") token : String, @Path("id") id : Long) : Response<PostResponse>
}