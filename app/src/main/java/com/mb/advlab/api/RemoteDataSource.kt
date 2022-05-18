package com.mb.advlab.api

import com.mb.advlab.model.request.*
import com.mb.advlab.utils.BaseDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val advLabService: AdvLabService) : BaseDataSource() {

    suspend fun signRequest(signupRequest: SignupRequest) = getResult {
        advLabService.signRequest(signupRequest)
    }

    suspend fun loginRequest(loginRequest: LoginRequest) = getResult {
        advLabService.loginRequest(loginRequest)
    }

    suspend fun getFolloweds(token : String, id :Long) = getResult {
        advLabService.getFollowedList(token, id)
    }

    suspend fun getFollowers(token : String, id :Long) = getResult {
        advLabService.getFollowerList(token, id)
    }

    suspend fun getCounts(token : String, id :Long) = getResult {
        advLabService.getCount(token, id)
    }

    suspend fun sharePost(token : String, id : Long, sharePost : SharePost) = getResult {
        advLabService.sharePost(token, id,sharePost)
    }

    suspend fun getUsersPost(token : String, id : Long) = getResult {
        advLabService.getUsersPost(token, id)
    }
    suspend fun followById(token : String, fromTo : FollowUnRequest) = getResult {
        advLabService.followById(token, fromTo)
    }

    suspend fun unfollowById(token : String, fromTo : FollowUnRequest) = getResult {
        advLabService.deleteById(token, fromTo)
    }

    suspend fun saveUserPhoto(token : String,id : Long, photoRequest :  MultipartBody.Part) = getResult {
        advLabService.saveUserPhoto(token, id,photoRequest)
    }

    suspend fun searchUsers(token : String, keyword : String) = getResult {
        advLabService.searchUser(token, keyword)
    }

    suspend fun findById(token : String,id : Long ) = getResult {
        advLabService.findById(token, id)
    }

    suspend fun getUserPhoto(token : String,id : Long) = getResult {
        advLabService.getUserPhoto(token, id)
    }
}