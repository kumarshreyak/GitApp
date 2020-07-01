package com.shrekiscool.githubapp.network

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {

    @GET("/repositories")
    fun getRepositories() : Observable<Response<ResponseBody>>
}