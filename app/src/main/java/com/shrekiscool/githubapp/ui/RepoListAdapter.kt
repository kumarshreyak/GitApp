package com.shrekiscool.githubapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shrekiscool.githubapp.R
import com.shrekiscool.githubapp.databinding.ItemRepoBinding
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse

class RepoListAdapter(private val context: Context, private val data: GetRepositoryResponse) :
    RecyclerView.Adapter<RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepoBinding>(LayoutInflater.from(context),
            R.layout.item_repo, parent, false)
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.binding.tvUser.text = data[position].author
        holder.binding.tvRepo.text = data[position].name
    }

}

class RepoViewHolder(var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root)

