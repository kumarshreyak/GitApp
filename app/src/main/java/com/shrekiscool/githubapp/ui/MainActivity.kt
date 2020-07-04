package com.shrekiscool.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
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
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainViewModel.getRepositories().observe(this) {
            if(it.isNotEmpty()) {

                binding.rvRepoList.adapter = RepoListAdapter(this, it)
            } else {
                binding.rvRepoList.visibility = GONE
                binding.tvFailure.visibility = VISIBLE
            }
        }
    }
}
