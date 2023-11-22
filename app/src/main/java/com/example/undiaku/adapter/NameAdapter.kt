package com.example.undiaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.undiaku.R
import com.example.undiaku.model.ListNameModel

class NameAdapter : ListAdapter<ListNameModel, NameAdapter.NameViewHolder>(NameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_name, parent, false)
        return NameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = getItem(position)
        holder.nameTextView.text = name.name
    }

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
    }

    class NameDiffCallback : DiffUtil.ItemCallback<ListNameModel>() {
        override fun areItemsTheSame(oldItem: ListNameModel, newItem: ListNameModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ListNameModel, newItem: ListNameModel): Boolean {
            return oldItem == newItem
        }
    }

}