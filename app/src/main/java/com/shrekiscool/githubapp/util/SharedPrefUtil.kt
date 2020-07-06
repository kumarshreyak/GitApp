package com.shrekiscool.githubapp.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.Gson
import com.securepreferences.SecurePreferences

/**
 * SharedPreferenceUtil:
 *
 * A Utility class to save SharedPreferences with SecurePreference.
 * It provides getters and setters for all possible types.
 *
 */

object SharedPreferenceUtil {
    // Constants for Keys
    val TIMESTAMP = "TIMESTAMP"


    // Util functions
    private var preferences: SharedPreferences? = null

    private fun getPreferences(context: Context): SharedPreferences {
        if (preferences == null) {
            preferences = SecurePreferences(
                context,
                SHARED_PREFERENCES_PASSWORD,
                SHARED_PREFERENCES_FILE_NAME
            )
        }
        return preferences!!
    }

    // Getters
    fun getStringSharedPreference(context: Context, key: String): String? {
        return getPreferences(context).getString(key, "")
    }

    fun getBooleanSharedPreference(context: Context, key: String): Boolean {
        return getPreferences(context).getBoolean(key, false)
    }

    fun getIntSharedPreference(context: Context, key: String): Int {
        return getPreferences(context).getInt(key, -1)
    }

    fun getLongSharedPreference(context: Context, key: String): Long {
        return getPreferences(context).getLong(key, -1)
    }

    fun getFloatSharedPreference(context: Context, key: String): Float {
        return getPreferences(context).getFloat(key, -1f)
    }

    fun getStringSetSharedPreference(context: Context, key: String): Set<String>? {
        return getPreferences(context).getStringSet(key, null)
    }


    // Setters
    fun setSharedPreference(context: Context, key: String, value: String) {
        val edit = getPreferences(context).edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun setSharedPreference(context: Context, key: String, value: Boolean) {
        val edit = getPreferences(context).edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun setSharedPreference(context: Context, key: String, value: Int) {
        val edit = getPreferences(context).edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun setSharedPreference(context: Context, key: String, value: Long) {
        val edit = getPreferences(context).edit()
        edit.putLong(key, value)
        edit.apply()
    }

    fun setSharedPreference(context: Context, key: String, value: Float) {
        val edit = getPreferences(context).edit()
        edit.putFloat(key, value)
        edit.apply()
    }

    fun setSharedPreference(context: Context, key: String, value: Set<String>) {
        val edit = getPreferences(context).edit()
        edit.putStringSet(key, value)
        edit.apply()
    }

    fun remove(context: Context, key: String) {
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = mPrefs.edit()
        prefsEditor.remove(key)
        prefsEditor.apply()
    }

    fun setSharedPrefObject(context: Context, `object`: Any) {
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(`object`)
        prefsEditor.putString("customer_details", json)
        prefsEditor.apply()
    }

    //Existing SharedPreferenceUtil does not work for remove hence this
    fun removeAppPermissions(key: String, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.remove(key)
        editor.clear()
        editor.apply()
    }
}
