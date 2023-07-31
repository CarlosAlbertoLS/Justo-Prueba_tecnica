package com.sonder.userlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonder.userlist.R
import com.sonder.userlist.data.network.model.Result

class UserAdapter(private var userLis: List<Result> = emptyList(),
                  private val onItemSelected: (String) -> Unit) :
    RecyclerView.Adapter<UserViewHolder>() {

    fun updateList(userLis: List<Result>){
        this.userLis = userLis
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount() = userLis.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userLis[position], onItemSelected)
    }
}