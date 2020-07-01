package com.shrekiscool.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.shrekiscool.githubapp.R
import com.shrekiscool.githubapp.databinding.ActivityMainBinding
import com.shrekiscool.githubapp.network.apiInterface
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initNetworkCall()
    }

    private fun initNetworkCall() {
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
                    Log.d("onNext", "Response recieved  - " + response.code().toString())
                    val gson = Gson()
                    if(response.code() == 200) {
                        // Success
                        val response = gson.fromJson(response.body()!!.string(), GetRepositoryResponse::class.java)
                        binding.networkTest.text = response[0].description
                    } else {
                        // Failure
//                        coolResponse.value = gson.fromJson(response.body()?.string(), CoolResponse::class.java)
                    }
                }

            })
    }
}
