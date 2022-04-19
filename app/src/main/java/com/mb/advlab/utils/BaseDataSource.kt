package com.mb.advlab.utils

import android.util.Log
import retrofit2.Response
import org.json.JSONArray

import org.json.JSONObject




abstract class BaseDataSource {

    suspend fun <T>  getResult(call : suspend() -> Response<T> ) : Resource<T>{

        val response = call()

        if (response.isSuccessful){
            if (response.body() != null){
                return Resource.succes(response.body()!!)
            }
        }

        val errorBody = response.errorBody()!!.charStream()
        return Resource.error(errorBody.readText())
    }
}