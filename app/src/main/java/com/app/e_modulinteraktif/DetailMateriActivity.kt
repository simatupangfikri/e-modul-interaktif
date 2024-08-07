package com.app.e_modulinteraktif

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase

class DetailMateriActivity : AppCompatActivity() {

    var databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_materi)

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val title = intent.getStringExtra("title")
        val username = sharedPreferences.getString("username","")

        var materiHtml = String()

        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toolbar.setSubtitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))

        toolbar.setNavigationOnClickListener { finish() }

        val materiText = findViewById<TextView>(R.id.materiText)
        val tanya = findViewById<TextView>(R.id.tanya)
        val komentar = findViewById<EditText>(R.id.komentar)
        val simpan = findViewById<Button>(R.id.simpan)

        if (title.equals("Eliminasi")){
            materiHtml = "<h4>1. Eliminasi </h4>\n" +
                    "\n" +
                    "<p>Eliminasi artinya membuang atau menghilangkan. SPLDV memiliki dua variabel, dengan membuang/menghilangkan atau mengeliminasi satu variabel kita memperoleh persamaan linear dengan satu variabel, yang mencari akarnya telah dipelajari dikelas VII. Persolannya, bagaimana cara mengeliminasi satu variabel tersebut.\n" +
                    "</p>\n" +
                    "\n" +
                    "<p>Langkah-langkah menyelesaikan SPLDV dengan eliminasi\n" +
                    "</p>\n" +
                    "\n" +
                    "\n" +
                    "  <p>Eliminasi adalah metode atau cara untuk menyelesaikan suatu sistem persamaan liniear dua variabel dengan cara mengeliminasi atau menghilangkan salah satu peubah/variabel dengan menyamakan koefisien dari persamaantersebut.</p>\n" +
                    "  <p>Cara menghilangkan salah satu variabel adalah dengan memperhatikan tandanya, apabila tandanya sama (+) dengan (+) atau (-) dengan (-), maka untuk mengelimanasinya dengan cara mengurangkan dan sebaliknya apabila tandanya berbeda maka gunakanlah sistem penjumlahan.</p>\n" +
                    "<p>Contoh soal SPLDV dengan metode eliminasi :</p>\n" +
                    "<p>Tentukan penyelesaian dari SPLDV 2x + y = 5 dan 3x + 4y = 10 !\n" +
                    "</p>\n" +
                    "\n" +
                    "<p>Penyelesaian:\n" +
                    "</p>\n" +
                    "\n" +
                    "<p>Ayo mengingat kembali bahwa suatu persamaan jika kedua ruasnya dikalikan bilangan yang sama akan diperoleh persamaan yang ekuivalen. Sekarang perhatikan SPLDV tersebut.\n" +
                    "</p>\n" +
                    "<p>2x + y = 5 ...............................…persamaan (1)\n" +
                    "</p>\n" +
                    "<p>3x + 4y = 10 ......................………persamaan (2)\n" +
                    "</p>\n" +
                    "<p>Pertama kita pilih yang akan dieliminasi yaitu variable x . Koefisien x pada masing – masing persamaan harus sama atau lawannya. Di sini koefisien x pada persamaan 1 adalah dan persamaan 2 adalah 3. Kelipatan persekutuan terkecil (KPK) dari 2 dan 3 adalah 6. Maka akan dijadikan koefisien x untuk kedua persamaan tersebut adalah 6. Agar koefisien x pada persamaan (1) menjadi 6, harus dikalikan 3 untuk kedua ruasnya. Sedangkan persamaan (2) menjadi 6, harus dikalikan 2 untuk kedua ruasnya.</p>\n" +
                    "<p>Persamaan(1) dikurangipersamaan (2) diperoleh:</p>\n" +
                    "<p>2x + y   = 5   3   6x + 3y = 15\n" +
                    "</p>\n" +
                    "<p>3x + 4y = 10  2  6x + 8y = 20</p>\n" +
                    "<p>y = 1</p>\n" +
                    "<p>Selanjutnya kita akan eliminasi variabel y, masing-masing koefisiennya 1 dan 4, dan kelipatan persekutuan terkecil (KPK) nya adalah 4. Maka akan dijadikan koefisien untuk kedua persamaan tersebut adalah 4. Agar koefisien persamaan (1) menjadi 4, harus dikalikan 4 untuk kedua ruasnya. Sedangkan persamaan (2) untuk menjadi 4, harus dikalikan 1 untuk kedua ruasnya. Persamaan (1) dikurangi persamaan (2) diperoleh\n" +
                    "</p>\n" +
                    "<p>2x + y   = 5   4   8x + 4y = 20</p>\n" +
                    "<p>3x + 4y = 10  1  3x + 4y = 10\n" +
                    "</p>\n" +
                    "<p>5x = 10</p>\n" +
                    "<p>x = 2</p>\n" +
                    "<p>Jadi, penyelesaian dari SPLDV tersebut adalah (2, 1).\n" +
                    "</p>"
            tanya.text = "Jadi menurut kamu bagaimana penyelesaian dengan cara eliminasi?"
        }
        else if (title.equals("Subsitusi")){


            materiHtml = "<h4>2. Substitusi\n" +
                    "</h4>\n" +
                    "<p>Substitusi artinya mengganti/menempatkan, cara substitusi dalam menyelesaikan SPLDV mengganti variabel yang satu dengan variabel lainnya sesuai dengan persamaan yang diberikan.\n" +
                    "</p>\n" +
                    "<p>Langkah-langkah untuk menyelesaikan SPLDV menggunakan substitusi berikut:\n" +
                    "</p>\n" +
                    "<p>Mengubah salah satu persamaan menjadi bentuk x = cy + d atau\n" +
                    "  y = ax + b\n" +
                    "</p>\n" +
                    "<p>a,b,c, dan d adalah nilai yang ada pada persamaan </p>\n" +
                    "<p>Carilah salah satu persamaan yang mudahdari 2 persamaan yang ada, itu triknya.</p>\n" +
                    "<p>Kemudian persamaannya disubtitusikan nilai atau.\n" +
                    "</p>\n" +
                    "<p>Selesaikan persamaan sehingga mendapat nilai atau.\n" +
                    "</p>\n" +
                    "<p>Memperoleh nilai variabel lain yang belum diketahui dengan hasil langkah sebelumnya.\n" +
                    "</p>\n" +
                    "\n" +
                    "<p>Contoh soal dengan penyelesain subtitusi : </p>\n" +
                    "\n" +
                    "<p>Tentukan himpunan penyelesaian dari 4x – 2y = 8 dan 3x + y = 6!\n" +
                    "</p>\n" +
                    "<p>Penyelesaian:\n" +
                    "</p>\n" +
                    "<p>Persamaan 4x – 2y = 8 disebut persamaan pertama dan 3x + y = 6 disebut persamaan kedua.\n" +
                    "</p>\n" +
                    "<p>4x – 2y = 8 ............................................... persamaan (1)\n" +
                    "</p>\n" +
                    "<p>3x + y  = 6.................................................persamaan (2)\n" +
                    "</p>\n" +
                    "<p>Persamaan 3x + y = 6 dapat ditulis sebagai y = 6 – 3x\n" +
                    "</p>\n" +
                    "<p>Subsitusi/gantilah y pada persamaan (1) oleh 6 – 3x , sehingga diperoleh\n" +
                    "</p>\n" +
                    "<p>4x – 2y = 8\n" +
                    "</p>\n" +
                    "<p>4x – 2(6 – 3x) = 8</p>\n" +
                    "<p>4x – 12 + 6x    = 8\n" +
                    "</p>\n" +
                    "<p>10x – 12  = 8\n" +
                    "</p>\n" +
                    "<p>10x = 20</p>\n" +
                    "<p>x = 2\n" +
                    "</p>\n" +
                    "<p>Nilai 2 diperoleh, selanjutnya substitusi nilai x ke persamaan y = 6 – 3x dengan 2,maka</p>\n" +
                    "<p>y = 6 – 3x\n" +
                    "</p>\n" +
                    "<p>y = 6 – 3(2)</p>\n" +
                    "<p>y = 0</p>\n" +
                    "<p>Jadi, penyelesaian SPLDVtersebut adalah (2, 0)\n" +
                    "</p>"
            tanya.text = "Jadi menurut kamu bagaimana penyelesaian dengan cara subsitusi?"
        }
        else{
            materiHtml = "<h4>3. Campuran</h4>\n" +
                    "<p>Metode campuran dapat gunakan jika mencari alternatif rumus yang menghasilkan perhitungan yang lebih cepat. Cara kerja metode ini yaitu melakukan eliminasi untuk mencari solusi suatu variabel. Setelah itu, melakukan substitusi variabel yang telah ditemukan untuk menghitung variabel berikutnya.\n" +
                    "</p>\n" +
                    "\n" +
                    "<p>Pemecahan Masalah Yang Berkaitan Dengan SPLDV</p>\n" +
                    "<p>Permasalahan sehari-hari dapat disajikan dalam matematika dalam bentuk Sistem Persamaan linear Dua Variabel (SPLDV). Kemudian untuk cara menyelesaikan masalah berkaitan dengan SPLDV banyak digunakan kombinasi antara cara eliminasi dan subsitusi.\n" +
                    "</p>\n" +
                    "<p>Contoh pemecahan masalah dengan SPLDV:\n" +
                    "</p>\n" +
                    "<p>Suatu pertunjukkan dikunjungi orang terdiri dari anak-anak dan orang dewasa. Harga tiket untuk anak-anak sedangkan harga tiket untuk orang dewasa. Dari semua pengunjung yang menghadiri pertunjukkan adalah. Tentukan banyak anak-anak dan orang dewasa yang menghadiri pertunjukkan.\n" +
                    "</p>\n" +
                    "<p>Penyelesaian:\n" +
                    "</p>\n" +
                    "<p>Misalkan\n" +
                    "</p>\n" +
                    "<p>x = banyaknya penonton anak-anak</p>\n" +
                    "<p>y = banyaknya penonton orang dewasa\n" +
                    "</p>\n" +
                    "<p>x + y = 480 ........................... persamaan (1)\n" +
                    "</p>\n" +
                    "<p>8.000x + 12.000y = 5.060.000 ..........................persamaan (2)\n" +
                    "</p>\n" +
                    "<p>Persamaan (2) 8.000x + 12.000y = 5.060.000  dapat disederhanakan menjadi 2x + 3y = 1.265 (kedua ruas dibagi dengan KPK dari 8.000, 12.000, dan 5.060.000 yaitu 400). Eliminasi variabel y maka\n" +
                    "</p>\n" +
                    "<p>x + y     = 480     3   3x + 3y = 1.440\n" +
                    "</p>\n" +
                    "<p>2x + 3y = 1.265  1  2x + 3y = 1.265\n" +
                    "</p>\n" +
                    "<p\n" +
                    "  >x = 175\n" +
                    "</p>\n" +
                    "<p>Selanjutnya substitusi atau memasukan x = 175 pada persamaan (1)\n" +
                    "</p>\n" +
                    "<p>175 + y     = 480 </p>\n" +
                    "<p> y     = 480 – 175\n" +
                    "</p>\n" +
                    "<p>y     = 305\n" +
                    "</p>\n" +
                    "<p>Jadi, banyaknya penonton anak-anak adalah 175 orang dan penonton dewasa 305 orang.\n" +
                    "</p>"
            tanya.text = "Jadi menurut kamu bagaimana penyelesaian dengan cara campuran?"
        }



        materiText.text = Html.fromHtml(materiHtml)

        simpan.setOnClickListener {
            if (checkNetworkConnection()) {
                if(komentar.text.isNotEmpty()){

                    val komen = komentar.text.toString()
                    if (username != null) {
                        if (title.equals("Eliminasi")){
                            databaseReference.child("users").child(username).child("eliminasi").setValue(komen)
                            databaseReference.child("users").child("list_pengguna").child(username).child("eliminasi").setValue(komen)
                            Toast.makeText(applicationContext, "Terimakasih atas tanggapan anda", Toast.LENGTH_SHORT)
                                .show()
                            komentar.text.clear()
                        }
                        else if (title.equals("Subsitusi")){
                            databaseReference.child("users").child(username).child("subsitusi").setValue(komen)
                            databaseReference.child("users").child("list_pengguna").child(username).child("subsitusi").setValue(komen)
                            Toast.makeText(applicationContext, "Terimakasih atas tanggapan anda", Toast.LENGTH_SHORT)
                                .show()
                            komentar.text.clear()
                        }
                        else{
                            databaseReference.child("users").child(username).child("campuran").setValue(komen)
                            databaseReference.child("users").child("list_pengguna").child(username).child("campuran").setValue(komen)
                            Toast.makeText(applicationContext, "Terimakasih atas tanggapan anda", Toast.LENGTH_SHORT)
                                .show()
                            komentar.text.clear()
                        }

                    }
                    else{
                        Toast.makeText(applicationContext, "Anda belum login", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                else{
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

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }

}