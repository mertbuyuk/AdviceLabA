package com.mb.advlab.viewmodel.post

import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.model.request.SharePost
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharePostViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    fun sharePost(token : String, id : Long, sharePost: SharePost) = apiRepository.sharePost(token, id,sharePost)
}