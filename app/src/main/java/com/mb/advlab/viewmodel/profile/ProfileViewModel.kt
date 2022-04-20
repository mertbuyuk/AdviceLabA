package com.mb.advlab.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.model.responses.Followeds
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    val followedList : LiveData<List<Followeds>>? =null
    val followerList : LiveData<List<Followeds>>? =null

    fun getFolloweds(token : String, id : Long) = apiRepository.getFolloweds(token, id)

    fun getFollowers(token : String, id : Long) = apiRepository.getFolloweds(token, id)
}