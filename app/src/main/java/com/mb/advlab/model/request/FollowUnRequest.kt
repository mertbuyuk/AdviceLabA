package com.mb.advlab.model.request

data class FollowUnRequest(
    private val fromId : Long,
    private val toId : Long
)