package com.shrekiscool.githubapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shrekiscool.githubapp.base.BaseViewModel
import com.shrekiscool.githubapp.network.apiInterface
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse
import com.shrekiscool.githubapp.util.GET_REPOSITORY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class MainViewModel : BaseViewModel() {
    private var getRepositoryResponse: MutableLiveData<GetRepositoryResponse> = MutableLiveData()
    var errorMessage: String? = null

    init {
        // Fetch Repos
        apiInterface.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getNewObserver())
    }

    override fun onSuccess(response: Response<ResponseBody>) {
        val url = response.raw().request().url().toString()
        if(url.endsWith(GET_REPOSITORY)) {
            getRepositoryResponse.value = gson.fromJson(response.body()!!.string(), GetRepositoryResponse::class.java)
        }
    }

    override fun onFailure(response: Response<ResponseBody>) {
        errorMessage = response.message()
    }

    fun getRepositories() : LiveData<GetRepositoryResponse> {
        return getRepositoryResponse
    }

}