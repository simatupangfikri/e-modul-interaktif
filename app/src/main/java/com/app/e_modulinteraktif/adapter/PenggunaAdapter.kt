package com.app.e_modulinteraktif.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.e_modulinteraktif.R
import com.app.e_modulinteraktif.model.Pengguna

class PenggunaAdapter(private val mContext: Context, arrayList: ArrayList<Pengguna>) :
    RecyclerView.Adapter<PenggunaAdapter.MyViewHolder>() {
    private var arrayList: ArrayList<Pengguna> = ArrayList<Pengguna>()

    init {
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_pengguna, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pengguna: Pengguna = arrayList[position]
        holder.username.text = pengguna.username
        holder.password.text = pengguna.password
        holder.nama.text = pengguna.nama

        holder.nomor.text = (position + 1).toString() + ". "
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username: TextView
        var password: TextView
        var nama: TextView

        var nomor: TextView

        init {
            username = itemView.findViewById<TextView>(R.id.username)
            password = itemView.findViewById<TextView>(R.id.password)
            nama = itemView.findViewById<TextView>(R.id.nama)

            nomor = itemView.findViewById<TextView>(R.id.nomor)
        }
    }
}