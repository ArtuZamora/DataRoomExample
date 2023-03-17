package com.dsm.dataroomexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dsm.dataroomexample.R
import com.dsm.dataroomexample.model.User

class AdapterUsers (
    val userLists: MutableList<User>,
    val listener: AdapterListener
        ): RecyclerView.Adapter<AdapterUsers.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userLists[position]
        holder.tvUsuario.text = user.user
        holder.tvPais.text = user.country

        holder.cvUsuario.setOnClickListener{
            listener.onEditItemClick(user)
        }
        holder.btnBorrar.setOnClickListener{
            listener.onDeleteItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return userLists.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvUsuario = itemView.findViewById<CardView>(R.id.cvUsuario)
        val tvUsuario = itemView.findViewById<TextView>(R.id.tvUsuario)
        val tvPais = itemView.findViewById<TextView>(R.id.tvPais)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
    }

}





