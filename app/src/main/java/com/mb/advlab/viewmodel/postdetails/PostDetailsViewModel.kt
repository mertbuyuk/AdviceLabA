package com.mb.advlab.viewmodel.postdetails

import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.api.ApiRepository_Factory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {

    fun getPostDetails(token : String, id : Long) = apiRepository.getPostDetails(token, id)
}