package com.app.e_modulinteraktif

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterActivity : AppCompatActivity() {

    var databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    lateinit var nama: EditText
    lateinit var username:EditText
    lateinit var password:EditText
    lateinit var confirmpassword:EditText
    lateinit var daftar: Button
    lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        nama = findViewById(R.id.nama)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        confirmpassword = findViewById(R.id.confirmpassword)
        daftar = findViewById(R.id.daftar)
        login = findViewById(R.id.login)

        daftar.setOnClickListener {
            val sUsername = username.text.toString()
            val sNama = nama.text.toString()
            val sPassword = password.text.toString()
            val sConfirmpassword = confirmpassword.text.toString()
            if (sNama == "" || sUsername == "" || sPassword == "" || sConfirmpassword == "") {
                nama.error = "Kolom harus terisi"
                username.error = "Kolom harus terisi"
                password.error = "Kolom harus terisi"
                confirmpassword.error = "Kolom harus terisi"
            } else {
                if (sPassword == sConfirmpassword) {
                    databaseReference.child("users")
                        .addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.hasChild(sUsername)) {
                                    Toast.makeText(this@RegisterActivity, "Username telah terdaftar", Toast.LENGTH_SHORT).show()
                                } else {
                                    databaseReference.child("users").child(sUsername).child("username").setValue(sUsername)
                                    databaseReference.child("users").child(sUsername).child("password").setValue(sPassword)
                                    databaseReference.child("users").child(sUsername).child("nama").setValue(sNama)
                                    databaseReference.child("users").child(sUsername).child("level").setValue("pengguna")
                                    databaseReference.child("users").child(sUsername).child("skor").setValue(0)
                                    databaseReference.child("users").child("list_pengguna").child(sUsername).child("username").setValue(sUsername)
                                    databaseReference.child("users").child("list_pengguna").child(sUsername).child("password").setValue(sPassword)
                                    databaseReference.child("users").child("list_pengguna").child(sUsername).child("nama").setValue(sNama)
                                    databaseReference.child("users").child("list_pengguna").child(sUsername).child("level").setValue("pengguna")
                                    databaseReference.child("users").child("list_pengguna").child(sUsername).child("skor").setValue(0)
                                    Toast.makeText(this@RegisterActivity, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@RegisterActivity, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                            }
                        })
                } else {
                    confirmpassword.error = "Password tidak sama"
                }
            }
        }

        login.setOnClickListener { finish() }

    }
}