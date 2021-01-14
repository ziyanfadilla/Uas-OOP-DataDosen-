package com.nurulziyan.finalprojectoop_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nurulziyan.finalprojectoop_2.R
import com.nurulziyan.finalprojectoop_2.model.NurulUser

class NurulAdapter (val context: Context):RecyclerView.Adapter<NurulAdapter.UserViewHolder>(){
    private var users: MutableList<NurulUser> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NurulAdapter.UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_nurul, parent,false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: NurulAdapter.UserViewHolder, position: Int) {
        holder.bindModel(users[position])
    }
    fun setUser(data: List<NurulUser>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(i : View): RecyclerView.ViewHolder(i){
        val tvId: TextView = i.findViewById(R.id.tv_id)
        val tvNama: TextView = i.findViewById(R.id.tv_nama)
        val tvNim: TextView = i.findViewById(R.id.tv_nim)
        fun bindModel(u: NurulUser){
            tvId.text=u.getId().toString()
            tvNama.text=u.getNama()
            tvNim.text=u.getNim()
        }
    }

}