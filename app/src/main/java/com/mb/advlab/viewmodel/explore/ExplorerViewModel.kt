package com.mb.advlab.viewmodel.explore

import androidx.lifecycle.ViewModel
import com.mb.advlab.api.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExplorerViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    fun searchUser(token : String, keyword : String) = apiRepository.searchUser(token, keyword)
}