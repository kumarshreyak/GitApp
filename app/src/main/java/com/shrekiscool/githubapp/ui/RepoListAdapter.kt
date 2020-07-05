package com.shrekiscool.githubapp.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shrekiscool.githubapp.R
import com.shrekiscool.githubapp.databinding.ItemRepoBinding
import com.shrekiscool.githubapp.network.response.GetRepositoryResponse
import com.squareup.picasso.Picasso

class RepoListAdapter(private val context: Context, private val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<RepoViewHolder>() {

    val data = mainViewModel.getRepositories().value!!

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
        Picasso.get().load(data[position].avatar).into(holder.binding.ivUser)

        // Expanded
        holder.binding.tvLang.text = data[position].language
        if(!data[position].languageColor.isNullOrEmpty())
            holder.binding.ivLang.setBackgroundColor(Color.parseColor(data[position].languageColor))
        holder.binding.tvFork.text = data[position].forks.toString()
        holder.binding.tvStars.text = data[position].stars.toString()

        if(mainViewModel.stateList!![position].equals(State.CLOSED)) {
            holder.binding.expandedGroup.visibility = GONE
        } else {
            holder.binding.expandedGroup.visibility = VISIBLE
        }

        // On click
        holder.binding.root.setOnClickListener {
            if(mainViewModel.stateList!![position].equals(State.CLOSED)) {
                holder.binding.expandedGroup.visibility = VISIBLE
                mainViewModel.stateList!![position] = State.EXPANDED
            } else {
                holder.binding.expandedGroup.visibility = GONE
                mainViewModel.stateList!![position] = State.CLOSED
            }
        }
        holder.setIsRecyclable(false)
    }

}

class RepoViewHolder(var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root)