package com.app.e_modulinteraktif

import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class Contoh3Activity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    var databaseReference = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    var storageReference = FirebaseStorage.getInstance().getReference("file")
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var sv: ScrollView
    lateinit var fileNametv: TextView
    private var fileUri: Uri? = null
    private var count: Int? = 0
    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contoh3)

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        fileNametv = findViewById(R.id.fileName)

        val addFile = findViewById<Button>(R.id.addFile)
        addFile.setOnClickListener {
            selectFile()
        }

        videoView = findViewById(R.id.videoView)
        sv = findViewById(R.id.sv)

        // Set the video file path or resource
        val videoPath =
            "android.resource://${packageName}/${R.raw.contoh3}" // Change "your_video_file" to your actual video file name

        // Set the video URI
        val videoUri = Uri.parse(videoPath)

        // Create a MediaController to add basic video controls (optional)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.hide()
        videoView.setMediaController(mediaController)

        // Set the video URI to the VideoView
        videoView.setVideoURI(videoUri)

        // Start playing the video
        videoView.start()

        videoView.setOnCompletionListener {
            sv.visibility = View.VISIBLE
        }

        val title = intent.getStringExtra("title")
        val username = sharedPreferences.getString("username", "")

        val komentar = findViewById<EditText>(R.id.komentar)
        val simpan = findViewById<Button>(R.id.simpan)

        simpan.setOnClickListener {
            if (checkNetworkConnection()) {
                if (komentar.text.isNotEmpty()) {

                    val komen = komentar.text.toString()
                    if (username != null) {
                        if (fileUri != null) {

                            val currentDate = Calendar.getInstance().time
                            val fileReference = storageReference.child(
                                "$username $currentDate." + getFileExtension(
                                    fileUri!!
                                )
                            )
                            fileReference.putFile(fileUri!!)
                                .addOnSuccessListener { taskSnapshot ->
                                    fileReference.downloadUrl.addOnSuccessListener { uri ->
                                        val downloadUrl = uri.toString()
                                        databaseReference.child("users").child(username)
                                            .child("komentarcontoh3").setValue(komen)
                                        databaseReference.child("users").child(username)
                                            .child("filecontoh3").setValue(downloadUrl)
                                        databaseReference.child("users").child("list_pengguna")
                                            .child(username).child("komentarcontoh3")
                                            .setValue(komen)
                                        databaseReference.child("users").child("list_pengguna")
                                            .child(username).child("filecontoh3")
                                            .setValue(downloadUrl)
                                        Toast.makeText(
                                            applicationContext,
                                            "Terimakasih atas tanggapan anda",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        komentar.text.clear()
                                        progressDialog?.dismiss()
                                        this.finish()
                                    }


                                }
                                .addOnProgressListener { taskSnapshot ->
                                    // Perbarui nilai progress
                                    val progress =
                                        (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()

                                    // Periksa apakah ProgressDialog sudah ada atau belum
                                    if (progressDialog == null) {
                                        // Jika belum ada, buat ProgressDialog
                                        progressDialog = ProgressDialog(this)
                                        progressDialog?.setMessage("Mengunggah file...")
                                        progressDialog?.setCancelable(false)
                                        progressDialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                                        progressDialog?.max = 100
                                        progressDialog?.show()
                                    }

                                    // Perbarui nilai progress di ProgressDialog
                                    progressDialog?.progress = progress
                                }
                                .addOnFailureListener { exception ->
                                    progressDialog?.dismiss()
                                    Toast.makeText(
                                        this,
                                        "Gagal mengunggah file: $exception",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Silahkan masukkan file",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Anda belum login", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Toast.makeText(applicationContext, "Masukkan komentar anda", Toast.LENGTH_SHORT)
                        .show()
                }

            } else {
                Toast.makeText(applicationContext, "Tidak ada koneksi internet", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun checkNetworkConnection(): Boolean {
        val connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    private fun selectFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*" // Semua jenis file
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            fileUri = data?.data
            val fileName = fileUri!!.lastPathSegment
            fileNametv.text = "Nama Dokumen: $fileName"
            fileNametv.visibility = View.VISIBLE
        }

    }

    private fun getFileExtension(uri: Uri): String? {
        val cR: ContentResolver = contentResolver
        val mime: MimeTypeMap = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }


}