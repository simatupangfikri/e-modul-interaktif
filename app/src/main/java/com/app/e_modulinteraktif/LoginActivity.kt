package com.app.e_modulinteraktif

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var login: Button
    lateinit var daftar: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        daftar = findViewById(R.id.daftar)
        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        login.setOnClickListener{
            val sUsername = username.getText().toString()
            val sPassword = password.getText().toString()
            if (sUsername == "" || sPassword == "") {
                username.setError("Kolom harus terisi")
                password.setError("Kolom harus terisi")
            } else {
                databaseReference.child("users")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.hasChild(sUsername)) {
                                val getPassword = snapshot.child(sUsername).child("password").getValue(String::class.java)
                                val getLevel = snapshot.child(sUsername).child("level").getValue(String::class.java)
                                val getNama = snapshot.child(sUsername).child("nama").getValue(String::class.java)
                                editor.putString("level", getLevel)
                                editor.putString("username", sUsername)
                                editor.putString("nama",getNama)
                                editor.apply()
                                if (getPassword == sPassword) {

                                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                                    editor.putString("login", "islogin")
                                    editor.apply()
                                    val intent = Intent(this@LoginActivity, GreetingActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this@LoginActivity, "Password Salah", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(this@LoginActivity, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {}
                    })
            }
        }

        daftar.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

    }

}