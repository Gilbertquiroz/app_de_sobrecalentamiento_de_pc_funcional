package com.example.appdesobrecalentamientodepc.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appdesobrecalentamientodepc.R
import com.example.appdesobrecalentamientodepc.models.dato

class AdapterDato (private var datos: ArrayList<dato>):
    RecyclerView.Adapter<AdapterDato.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val temperatura: TextView = itemView.findViewById(R.id.tvTem)
        val velocidad: TextView = itemView.findViewById(R.id.tvVel)
        val estado: TextView = itemView.findViewById(R.id.tvEs)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDato.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_datos,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterDato.ViewHolder, position: Int) {
        val dato = datos[position]

        holder.temperatura.text = dato.temperatura
        holder.velocidad.text = dato.velocidad
        holder.estado.text = dato.estado

    }

    override fun getItemCount(): Int {
        return datos.size
    }
}