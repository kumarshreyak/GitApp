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
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : BaseViewModel() {
    private var getRepositoryResponse: MutableLiveData<GetRepositoryResponse> = MutableLiveData()
    var stateList: ArrayList<State>? = null
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
//            stateList = ArrayList<State>().also { sList ->
//                repeat(getRepositories().value!!.size) {
//                    sList.add(State.CLOSED)
//                }
//            }
        }
    }

    override fun onFailure(response: Response<ResponseBody>) {
        errorMessage = response.message()
    }

    fun getRepositories() : LiveData<GetRepositoryResponse> {
        return getRepositoryResponse
    }

}

enum class State {
    CLOSED,
    EXPANDED
}