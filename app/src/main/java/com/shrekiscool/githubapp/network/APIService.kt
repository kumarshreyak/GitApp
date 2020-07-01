package com.shrekiscool.githubapp.network

import com.shrekiscool.githubapp.util.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    var apiInterface: APIInterface = Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .client(OkHttpClient())
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(APIInterface::class.java)
}