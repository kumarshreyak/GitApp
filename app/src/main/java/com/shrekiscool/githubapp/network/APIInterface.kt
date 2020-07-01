package com.shrekiscool.githubapp.network

import com.shrekiscool.githubapp.util.GET_REPOSITORY
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {

    @GET(GET_REPOSITORY)
    fun getRepositories() : Observable<Response<ResponseBody>>
}