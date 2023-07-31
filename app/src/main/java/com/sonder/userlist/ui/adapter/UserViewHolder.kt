package com.sonder.userlist.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sonder.userlist.data.network.model.Result
import com.sonder.userlist.databinding.ItemUserBinding

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserBinding.bind(view)
    fun bind(userResponse: Result, onItemSelected: (String) -> Unit) {
        binding.tvUserName.text = userResponse.name.first
        Glide.with(binding.tvUserName.context).load(userResponse.picture.large)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
            .into(binding.ivUserPhoto)
        binding.root.setOnClickListener { onItemSelected(userResponse.gender) }
    }
}