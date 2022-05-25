package com.mb.advlab.adapters

import com.mb.advlab.model.responses.FolloweDDetails
import com.mb.advlab.model.responses.PostResponse

interface IOnClick {

    fun onClick(item : FolloweDDetails)


    fun onClick(item : PostResponse)
}