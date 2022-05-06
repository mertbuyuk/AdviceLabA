package com.mb.advlab.api

import com.mb.advlab.model.request.FollowUnRequest
import com.mb.advlab.model.request.LoginRequest
import com.mb.advlab.model.request.SharePost
import com.mb.advlab.model.request.SignupRequest
import com.mb.advlab.utils.BaseDataSource
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
}