package com.mb.advlab.utils

import android.content.Context

class SharedPrefManager {

    fun setSharedPreference(context: Context, key: String?, value: String?) {
        val sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun getSharedPreference(context: Context, key: String?, defaultValue: String?): String? {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getString(key, defaultValue)
    }
    fun setSharedPreferenceBool(context: Context, key: String?, value: Boolean) {
        val sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun getSharedPreferenceBool(context: Context, key: String, defaultValue: Boolean): Boolean? {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getBoolean(key, defaultValue)
    }


    fun clearSharedPreference(context: Context) {
        val sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.clear()
        edit.apply()
    }

    fun removeSharedPreference(context: Context, key: String?) {
        val sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.remove(key)
        edit.apply()
    }
}