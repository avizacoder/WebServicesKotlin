package com.ageen.webservice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

        private var repos: List<Repo> = emptyList()

        fun setNameList(repos: List<Repo>) {
                this.repos = repos
                notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item, parent, false))
        }
        override fun getItemCount() = repos.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val repo = repos[position]
                holder.itemView.findViewById<TextView>(R.id.text_id).text = repo.id.toString()
                holder.itemView.findViewById<TextView>(R.id.text_name).text = repo.name

        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}