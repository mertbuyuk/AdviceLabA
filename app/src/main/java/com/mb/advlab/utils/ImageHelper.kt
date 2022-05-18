package com.mb.advlab.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class ImageHelper {

    fun stringToBitmap(encoded : String?) : Bitmap{

        val imageBytes = Base64.decode(encoded,0);
        return BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
       }
}