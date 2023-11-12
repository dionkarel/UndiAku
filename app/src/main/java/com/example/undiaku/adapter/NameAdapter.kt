package com.example.undiaku.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.undiaku.R
import com.example.undiaku.model.ListNameModel

class NameAdapter(private val names: ArrayList<ListNameModel>) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    inner class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_name, parent, false)
        return NameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = names[position]
        holder.nameTextView.text = name.name
    }

    override fun getItemCount() = names.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ListNameModel>) {
        names.clear()
        names.addAll(newData)
        notifyDataSetChanged()
    }
}