package com.app.e_modulinteraktif

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.e_modulinteraktif.adapter.PenggunaAdapter
import com.app.e_modulinteraktif.model.Pengguna
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DaftarPenggunaActivity : AppCompatActivity() {

    var databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: PenggunaAdapter
    lateinit var arrayList: ArrayList<Pengguna>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pengguna)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        arrayList = java.util.ArrayList<Pengguna>()
        recyclerView = findViewById(R.id.recyclerPengguna)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = PenggunaAdapter(this, arrayList)
        recyclerView.adapter = adapter

        databaseReference.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    if (dataSnapshot.child("level")
                            .getValue(String::class.java) == "pengguna"
                    ) {
                        val pengguna = dataSnapshot.getValue(Pengguna::class.java)
                        if (pengguna != null) {
                            arrayList.add(pengguna)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }
}