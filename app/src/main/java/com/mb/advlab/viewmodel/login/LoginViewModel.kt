package com.mb.advlab.viewmodel.login

import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.model.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {

    fun loginRequest(loginRequest: LoginRequest) = apiRepository.loginRequest(loginRequest)
}