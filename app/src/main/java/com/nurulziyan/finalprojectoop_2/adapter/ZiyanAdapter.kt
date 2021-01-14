package com.nurulziyan.finalprojectoop_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nurulziyan.finalprojectoop_2.R
import com.nurulziyan.finalprojectoop_2.model.NurulUser
import com.nurulziyan.finalprojectoop_2.model.ZiyanDosen

//adapter komponen recycleview = menyeting / wadah untuk menampilkan sebuah data
class ZiyanAdapter (val context: Context):RecyclerView.Adapter<ZiyanAdapter.DosenViewHolder>(){
    private var dosen: MutableList<ZiyanDosen> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ZiyanAdapter.DosenViewHolder {
        return DosenViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ziyan, parent,false))
    }

    override fun getItemCount(): Int {
        return dosen.size
    }

    override fun onBindViewHolder(holder: ZiyanAdapter.DosenViewHolder, position: Int) {
        holder.bindModel(dosen[position])
    }
    //untuk memastikan data ada atau tidak
    fun setDosen(data: List<ZiyanDosen>){
        dosen.clear()
        dosen.addAll(data)
        notifyDataSetChanged()
    }

    inner class DosenViewHolder(i : View):RecyclerView.ViewHolder(i){
        val tvId: TextView = i.findViewById(R.id.tv_id) //
        val tvNama: TextView = i.findViewById(R.id.tv_nama)
        val tvNipy: TextView = i.findViewById(R.id.tv_nipy)
        fun bindModel(u: ZiyanDosen){
            tvId.text=u.getId().toString()
            tvNama.text=u.getNama()
            tvNipy.text=u.getNipy()
        }
    }
}