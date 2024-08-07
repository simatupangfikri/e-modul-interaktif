package com.app.e_modulinteraktif.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Intent
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.e_modulinteraktif.*
import com.app.e_modulinteraktif.model.Menu
import java.util.ArrayList


class MainAdapter(private val context: Context, private val itemList: ArrayList<Menu>) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menu = itemList[position]
        holder.title.text = menu.title
        holder.constraint.setOnClickListener {
            val id = menu.id
            if (id == 1) {
                val intent = Intent(context, KompetensiActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("judul", menu.title)
                intent.putExtra("id", id)
                context.startActivity(intent)
            } else if (id == 2) {
                val intent = Intent(context, SegiEmpatActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("judul", menu.title)
                intent.putExtra("id", id)
                context.startActivity(intent)
            } else if (id == 5) {
                val intent = Intent(context, LatihanActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else if (id == 6) {
                val intent = Intent(context, ProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else if (id == 7) {
                val intent = Intent(context, TentangActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            else if (id == 8) {
                val intent = Intent(context, DaftarPenggunaActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }else if (id == 9) {
                val intent = Intent(context, DaftarSkorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }else if (id == 10) {
                val intent = Intent(context, DaftarKomentarActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }

            else {
                val intent = Intent(context, DetailActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("judul", menu.title)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView
        val constraint: ConstraintLayout

        init {

            title = itemView.findViewById(R.id.text)
            constraint = itemView.findViewById(R.id.constraint)
        }
    }
}

