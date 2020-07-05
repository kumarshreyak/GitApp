package com.shrekiscool.githubapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseRepository {
    val gson = Gson()

    abstract fun onSuccess(response: Response<ResponseBody>)

    abstract fun onFailure(response: Response<ResponseBody>)

    fun getNewObserver(): Observer<Response<ResponseBody>> {
        return object: Observer<Response<ResponseBody>> {
            override fun onSubscribe(d: Disposable) {
                Log.d("onSubscribe", "Subscribed !!")
            }

            override fun onError(e: Throwable) {
                Log.d("onError", e.message.toString())
            }

            override fun onComplete() {
                Log.d("onComplete", "Completed !!")
            }

            override fun onNext(response: Response<ResponseBody>) {
                Log.d("onNext", "Response recieved  - " + response.code().toString())
                val gson = Gson()
                if(response.code() == 200) {
                    // Success
                    onSuccess(response)
                } else {
                    // Failure
                    onFailure(response)
                }
            }
        }
    }
}