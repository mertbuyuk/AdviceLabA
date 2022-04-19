package com.mb.advlab.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> networkOperation(call : suspend () -> Resource<T>) : LiveData<Resource<T>>{
    return liveData(Dispatchers.IO){

        emit(Resource.loading())

        val networkCall = call.invoke()

        if (networkCall.status == Resource.Status.SUCCES){
            emit(Resource.succes(networkCall.data!!))
        }
        else if (networkCall.status == Resource.Status.ERROR){
            emit(
                Resource.error(networkCall.message!!))
        }
    }
}