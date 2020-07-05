package com.shrekiscool.githubapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse
import kotlin.collections.ArrayList

class MainViewModel() : ViewModel() {
    val getRepositoryResponse: MutableLiveData<GetRepositoryResponse> = MainRepository.getRepositories()
    var stateList: MutableLiveData<ArrayList<State>> = MutableLiveData()

    init {
        getRepositoryResponse.observeForever {
            stateList.value = ArrayList<State>().also { sList ->
                                repeat(getRepositoryResponse.value!!.size) {
                    sList.add(State.CLOSED)
                }
            }
        }

    }
}

enum class State {
    CLOSED,
    EXPANDED
}