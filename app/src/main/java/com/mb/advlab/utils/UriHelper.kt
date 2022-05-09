package com.mb.advlab.utils

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.util.*

class UriHelper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToString(bitmap: Bitmap) : String{

        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)
        val bArray = outputStream.toByteArray()

        val encodedString = Base64.getEncoder().encodeToString(bArray)

        Log.i("encoded",encodedString)

        return encodedString
    }
}