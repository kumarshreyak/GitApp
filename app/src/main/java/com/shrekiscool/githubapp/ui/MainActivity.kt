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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Show options menu
        binding.toolbar.inflateMenu(R.menu.main_menu)
        // Setup error group
        binding.errorGroup.visibility = GONE
        binding.btnRetry.setOnClickListener {
            MainRepository.getRepositories()
        }
        // Observer data
        mainViewModel.getRepositoryResponse.observe(this) {
            if(it.isNotEmpty()) {
                binding.rvRepoList.adapter = RepoListAdapter(this, mainViewModel)
                binding.rvRepoList.visibility = VISIBLE
                binding.errorGroup.visibility = GONE
            } else if(it.errorMessage.equals("error")) {
                binding.rvRepoList.visibility = GONE
                binding.errorGroup.visibility = VISIBLE
            }
        }
    }
}
