package com.mb.advlab.api

import com.mb.advlab.model.LoginRequest
import com.mb.advlab.model.SignupRequest
import com.mb.advlab.utils.networkOperation
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
}