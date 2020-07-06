package com.shrekiscool.githubapp.util

import android.util.Log
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import retrofit2.Response

const val BASE_URL = "https://ghapi.huchen.dev"
const val GET_REPOSITORY = "repositories"
const val SHARED_PREFERENCES_PASSWORD = "something"
const val SHARED_PREFERENCES_FILE_NAME = "sharedPrefFile"