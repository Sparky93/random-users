package com.example.randomuser.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.databinding.ItemListBinding

class ListAdapter(val onBottomReached: () -> Unit, private val threshold: Int) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val randomUsers: MutableList<RandomUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.randomUser = randomUsers[position]

        if (holder.adapterPosition == itemCount - threshold) {
            onBottomReached()
        }
    }

    override fun getItemCount(): Int = randomUsers.size

    fun setData(list: List<RandomUser>) {
        val diffCallback = ListCallback(randomUsers, list)
        val diffRandomUsers = DiffUtil.calculateDiff(diffCallback)

        randomUsers.clear()
        randomUsers.addAll(list)

        diffRandomUsers.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(containerView: View, val binding: ItemListBinding) :
        RecyclerView.ViewHolder(containerView)
}