package com.app.e_modulinteraktif.adapter

import android.app.DownloadManager
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.e_modulinteraktif.R
import com.app.e_modulinteraktif.model.Pengguna
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

class KomentarAdapter(private val mContext: Context, arrayList: ArrayList<Pengguna>) :
    RecyclerView.Adapter<KomentarAdapter.MyViewHolder>() {
    private var arrayList: ArrayList<Pengguna> = ArrayList<Pengguna>()

    init {
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_komentar, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pengguna: Pengguna = arrayList[position]
        holder.nama.text = pengguna.nama
        holder.komentarmateri1.setText(pengguna.komentarmateri1)
        holder.komentarmateri2.setText(pengguna.komentarmateri2)
        holder.komentarcontoh1.setText(pengguna.komentarcontoh1)
        holder.komentarcontoh2.setText(pengguna.komentarcontoh2)
        holder.komentarcontoh3.setText(pengguna.komentarcontoh3)

        if (pengguna.filemateri1 == null) {
            holder.filemateri1.visibility = View.GONE
        } else {
            holder.filemateri1.visibility = View.VISIBLE
            holder.filemateri1.setOnClickListener {
                val downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val downloadUri = Uri.parse(pengguna.filemateri1)
                val request = DownloadManager.Request(downloadUri)

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle(pengguna.username)
                request.setDescription("Downloading file")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    pengguna.username
                )

                downloadManager.enqueue(request)
                Toast.makeText(mContext, "Download dimulai", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        if (pengguna.filemateri2 == null) {
            holder.filemateri2.visibility = View.GONE
        } else {
            holder.filemateri2.visibility = View.VISIBLE
            holder.filemateri2.setOnClickListener {
                val downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val downloadUri = Uri.parse(pengguna.filemateri2)
                val request = DownloadManager.Request(downloadUri)

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle(pengguna.username)
                request.setDescription("Downloading file")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    pengguna.username
                )

                downloadManager.enqueue(request)
                Toast.makeText(mContext, "Download dimulai", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        if (pengguna.filecontoh1 == null) {
            holder.filecontoh1.visibility = View.GONE
        } else {
            holder.filecontoh1.visibility = View.VISIBLE
            holder.filecontoh1.setOnClickListener {
                val downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

                val downloadUri = Uri.parse(pengguna.filecontoh1)
                val request = DownloadManager.Request(downloadUri)

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle(pengguna.username)
                request.setDescription("Downloading file")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    pengguna.username
                )

                downloadManager.enqueue(request)
                Toast.makeText(mContext, "Download dimulai", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        if (pengguna.filecontoh2 == null) {
            holder.filecontoh2.visibility = View.GONE
        } else {
            holder.filecontoh2.visibility = View.VISIBLE
            holder.filecontoh2.setOnClickListener {
                val downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

                val downloadUri = Uri.parse(pengguna.filecontoh2)
                val request = DownloadManager.Request(downloadUri)

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle(pengguna.username)
                request.setDescription("Downloading file")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    pengguna.username
                )

                downloadManager.enqueue(request)
                Toast.makeText(mContext, "Download dimulai", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        if (pengguna.filecontoh3 == null) {
            holder.filecontoh3.visibility = View.GONE
        } else {
            holder.filecontoh3.visibility = View.VISIBLE
            holder.filecontoh3.setOnClickListener {
                val downloadManager =
                    mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

                val downloadUri = Uri.parse(pengguna.filecontoh3)
                val request = DownloadManager.Request(downloadUri)

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                request.setTitle(pengguna.username)
                request.setDescription("Downloading file")
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    pengguna.username
                )

                downloadManager.enqueue(request)
                Toast.makeText(mContext, "Download dimulai", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nama: TextView
        var komentarmateri1: EditText
        var komentarmateri2: EditText
        var komentarcontoh1: EditText
        var komentarcontoh2: EditText
        var komentarcontoh3: EditText
        var filemateri1: Button
        var filemateri2: Button
        var filecontoh1: Button
        var filecontoh2: Button
        var filecontoh3: Button

        init {
            nama = itemView.findViewById<TextView>(R.id.nama)
            komentarmateri1 = itemView.findViewById<EditText>(R.id.komentarmateri1)
            komentarmateri2 = itemView.findViewById<EditText>(R.id.komentarmateri2)
            komentarcontoh1 = itemView.findViewById<EditText>(R.id.komentarcontoh1)
            komentarcontoh2 = itemView.findViewById<EditText>(R.id.komentarcontoh2)
            komentarcontoh3 = itemView.findViewById<EditText>(R.id.komentarcontoh3)
            filemateri1 = itemView.findViewById(R.id.filemateri1)
            filemateri2 = itemView.findViewById(R.id.filemateri2)
            filecontoh1 = itemView.findViewById(R.id.filecontoh1)
            filecontoh2 = itemView.findViewById(R.id.filecontoh2)
            filecontoh3 = itemView.findViewById(R.id.filecontoh3)
        }
    }
}