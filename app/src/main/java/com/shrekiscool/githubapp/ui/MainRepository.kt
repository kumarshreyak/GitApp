package com.shrekiscool.githubapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.shrekiscool.githubapp.base.BaseRepository
import com.shrekiscool.githubapp.network.apiInterface
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse
import com.shrekiscool.githubapp.util.GET_REPOSITORY
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

object MainRepository {

    fun getRepositories() : MutableLiveData<GetRepositoryResponse> {
        val data: MutableLiveData<GetRepositoryResponse> = MutableLiveData()
        apiInterface.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<Response<ResponseBody>> {
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
                    Log.d("onNext", "Response recieved")
                    val gson = Gson()
                    if(response.code() == 200) {
                        // Success
                        data.value = gson.fromJson(response.body()!!.string(), GetRepositoryResponse::class.java)
                    } else {
                        // Failure
                        val errorData = GetRepositoryResponse()
                        errorData.errorMessage = response.message()
                        data.value = errorData
                    }
                }
            })
        return data
    }

}