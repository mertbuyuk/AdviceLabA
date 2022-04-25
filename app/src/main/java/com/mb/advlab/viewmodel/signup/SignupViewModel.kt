package com.mb.advlab.viewmodel.signup

import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.model.request.SignupRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    fun signRequest(signupRequest: SignupRequest) = apiRepository.signupRequest(signupRequest)
}