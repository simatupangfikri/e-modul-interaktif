package com.app.e_modulinteraktif.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.e_modulinteraktif.R
import com.app.e_modulinteraktif.model.Pengguna

class SkorAdapter(private val mContext: Context, arrayList: ArrayList<Pengguna>) :
    RecyclerView.Adapter<SkorAdapter.MyViewHolder>() {
    private var arrayList: ArrayList<Pengguna> = ArrayList<Pengguna>()

    init {
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_skor, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkorAdapter.MyViewHolder, position: Int) {
        val pengguna: Pengguna = arrayList[position]
        holder.nama.text = pengguna.nama
        holder.skor.text = pengguna.skor.toString()
        holder.nomor.text = (position + 1).toString() + ". "
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nama: TextView
        var skor: TextView
        var nomor: TextView

        init {
            nama = itemView.findViewById<TextView>(R.id.nama)
            skor = itemView.findViewById<TextView>(R.id.skor)
            nomor = itemView.findViewById<TextView>(R.id.nomor)
        }
    }
}