package com.example.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userssp.databinding.ItemUserAltBinding

class UserAdapter(private val users:List<User>,private val listener:OnClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context

        val view=LayoutInflater.from(context).inflate(R.layout.item_user_alt,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users= users[position]
        val humanPosition=position+1

        with(holder){
            setListener(users,humanPosition)
            binding.tvOrder.text=humanPosition.toString()
            binding.tvName.text=users.getFullName()
            Glide.with(context)
                .load(users.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int =users.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding= ItemUserAltBinding.bind(view)

        fun setListener(user:User,position: Int){
            binding.root.setOnClickListener { listener.onClick(user,position) }
        }
    }
}