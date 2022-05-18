package com.mb.advlab.api

import com.mb.advlab.model.request.*
import com.mb.advlab.utils.networkOperation
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ApiRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun signupRequest(signupRequest: SignupRequest) = networkOperation(
        call = {
            remoteDataSource.signRequest(signupRequest)
        }
    )

    fun loginRequest(loginRequest: LoginRequest) = networkOperation  (
        call = {
            remoteDataSource.loginRequest(loginRequest)
        })

    fun getFolloweds(token : String, id : Long) = networkOperation(
        call = {
            remoteDataSource.getFolloweds(token, id)
        }
    )

    fun getFollowers(token : String, id : Long) = networkOperation(
        call = {
            remoteDataSource.getFollowers(token, id)
        }
    )

    fun getCounts(token : String, id : Long) = networkOperation(
        call = {
            remoteDataSource.getCounts(token, id)
        }
    )

    fun sharePost(token: String, id: Long, sharePost: SharePost) = networkOperation (
        call = {
            remoteDataSource.sharePost(token, id, sharePost)
        }
            )


    fun getUsersPost(token : String, id : Long) = networkOperation(
        call = {
            remoteDataSource.getUsersPost(token, id)
        }
    )

    fun followById(token : String, fromTo : FollowUnRequest) = networkOperation (
        call = {
            remoteDataSource.followById(token, fromTo)
        }
            )

    fun unfollowById(token: String, fromTo: FollowUnRequest) = networkOperation(
        call = {
            remoteDataSource.unfollowById(token, fromTo)
        }
    )

    fun saveUserPhoto(token: String, id : Long ,photoRequest:  MultipartBody.Part) = networkOperation(
        call = {
            remoteDataSource.saveUserPhoto(token, id, photoRequest)
        }
    )


    fun getUserPhoto(token: String, id : Long) = networkOperation(
        call = {
            remoteDataSource.getUserPhoto(token, id)
        }
    )

    fun searchUser(token: String, keyword: String) = networkOperation(
        call = {
            remoteDataSource.searchUsers(token, keyword)
        }
    )

    fun findById(token: String, id: Long) = networkOperation(
        call = {
            remoteDataSource.findById(token, id)
        }
    )
}