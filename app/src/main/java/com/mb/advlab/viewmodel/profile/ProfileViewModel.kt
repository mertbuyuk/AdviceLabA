package com.mb.advlab.viewmodel.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import com.mb.advlab.model.request.FollowUnRequest
import com.mb.advlab.model.responses.Followeds
import com.mb.advlab.model.responses.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    val followedList : LiveData<List<Followeds>>? =null
    val followerList : LiveData<List<Followeds>>? =null

    val postList : MutableLiveData<List<PostResponse>>?  = null

    fun getFolloweds(token : String, id : Long) = apiRepository.getFolloweds(token, id)

    fun getFollowers(token : String, id : Long) = apiRepository.getFollowers(token, id)

    fun getCounts(token : String, id : Long) = apiRepository.getCounts(token, id)

    fun getUsersPost(token : String, id : Long) = apiRepository.getUsersPost(token, id)

    fun followById(token: String, fromTo : FollowUnRequest) = apiRepository.followById(token, fromTo)

    fun unfollowById(token: String, fromTo: FollowUnRequest) = apiRepository.unfollowById(token, fromTo)
}