package com.mb.advlab.api

import com.mb.advlab.model.SignupRequest
import com.mb.advlab.utils.networkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun signupRequest(signupRequest: SignupRequest) = networkOperation(
        call = {
            remoteDataSource.signRequest(signupRequest)
        }
    )
}