package com.mb.advlab.api

import com.mb.advlab.model.LoginRequest
import com.mb.advlab.model.SignupRequest
import com.mb.advlab.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val advLabService: AdvLabService) : BaseDataSource() {

    suspend fun signRequest(signupRequest: SignupRequest) = getResult {
        advLabService.signRequest(signupRequest)
    }

    suspend fun loginRequest(loginRequest: LoginRequest) = getResult {
        advLabService.loginRequest(loginRequest)
    }
}