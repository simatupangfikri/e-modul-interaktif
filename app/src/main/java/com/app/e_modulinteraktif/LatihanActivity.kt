package com.app.e_modulinteraktif

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager

import android.widget.*
import androidx.appcompat.widget.Toolbar

import androidx.lifecycle.ViewModelProvider

import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONException
import org.json.JSONObject


class LatihanActivity : AppCompatActivity() {

    var databaseReference = FirebaseDatabase.getInstance()
        .getReferenceFromUrl("https://e-modul-interaktif-e4906-default-rtdb.firebaseio.com/")
    private lateinit var dialog: Dialog
    private lateinit var viewModel: LatihanViewModel
    private lateinit var opsiA: Button
    private lateinit var opsiB: Button
    private lateinit var opsiC: Button
    private lateinit var opsiD: Button

    private lateinit var word_count: TextView
    private lateinit var score: TextView
    private lateinit var soal: TextView
    lateinit var mediaPlayer: MediaPlayer
    private var isMuted = false
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.music)
        mediaPlayer.setLooping(true)

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        viewModel = ViewModelProvider(this).get(LatihanViewModel::class.java)

        opsiA = findViewById(R.id.opsiA)
        opsiB = findViewById(R.id.opsiB)
        opsiC = findViewById(R.id.opsiC)
        opsiD = findViewById(R.id.opsiD)


        word_count = findViewById(R.id.word_count)
        score = findViewById(R.id.score)
        soal = findViewById(R.id.soal)

        Toast.makeText(
            applicationContext, "Selamat Mengerjakan", Toast.LENGTH_SHORT
        ).show()

        dataTampil()

        opsiA.setOnClickListener { onSubmitAnswer("A") }
        opsiB.setOnClickListener { onSubmitAnswer("B") }
        opsiC.setOnClickListener { onSubmitAnswer("C") }
        opsiD.setOnClickListener { onSubmitAnswer("D") }


        checkSound()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sound, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.mute) {
            setIcon(item)
            isMuted = !isMuted
            checkSound()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun checkSound() {
        if (isMuted) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    private fun setIcon(item: MenuItem?) {
        if (item == null) return
        if (isMuted) {
            item.setIcon(R.drawable.ic_baseline_volume_up_24)
        } else {
            item.setIcon(R.drawable.ic_baseline_volume_off_24)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    private fun dataTampil() {
        word_count.text = "${viewModel.curentQuestionCount.value} dari 5 Soal"
        score.text = "Score : ${viewModel.score.value}"
        soal.text = "${viewModel.currentQuestion.value!!}"

        opsiA.text = "${viewModel.currentAnswer.value?.get(0)}"
        opsiB.text = "${viewModel.currentAnswer.value?.get(1)}"
        opsiC.text = "${viewModel.currentAnswer.value?.get(2)}"
        opsiD.text = "${viewModel.currentAnswer.value?.get(3)}"

    }

    private fun onSubmitAnswer(answer: String) {
        if (viewModel.isUserCorrect(answer)) {
            showCorrectDialog()
        } else {
            showWrongDialog()
        }
    }

    private fun showCorrectDialog() {
        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.correct)
        mediaPlayer.setLooping(false)
        mediaPlayer.start()

        val correctDialog = Dialog(this)
        correctDialog.setContentView(R.layout.correct_dialog)
        correctDialog.setCancelable(false)
        correctDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val descripCorrect = correctDialog.findViewById<TextView>(R.id.descrip)
        descripCorrect.text = "${viewModel.currentDescrip.value!!}"

        val nextCorrect = correctDialog.findViewById<Button>(R.id.next)
        nextCorrect.setOnClickListener {
            correctDialog.dismiss()

            if (!viewModel.NextQuestion()) {
                showFinishDialog()
            } else {
                dataTampil()
            }
        }
        correctDialog.show()
    }

    private fun showWrongDialog() {
        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.wrong)
        mediaPlayer.setLooping(false)
        mediaPlayer.start()

        val wrongDialog = Dialog(this)
        wrongDialog.setContentView(R.layout.wrong_dialog)
        wrongDialog.setCancelable(false)
        wrongDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val descripWrong = wrongDialog.findViewById<TextView>(R.id.descrip)
        descripWrong.text = "${viewModel.currentDescrip.value!!}"

        val wrongCorrect = wrongDialog.findViewById<Button>(R.id.next)
        wrongCorrect.setOnClickListener {
            wrongDialog.dismiss()

            if (!viewModel.NextQuestion()) {
                showFinishDialog()
            } else {
                dataTampil()
            }
        }
        wrongDialog.show()
    }

    private fun showFinishDialog() {
        val scoreDialog = Dialog(this)
        scoreDialog.setContentView(R.layout.score_dialog)
        scoreDialog.setCancelable(false)
        scoreDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val name = scoreDialog.findViewById<TextView>(R.id.name)
        val score = scoreDialog.findViewById<TextView>(R.id.score)
        val back: Button = scoreDialog.findViewById(R.id.back)


        val username = sharedPreferences.getString("username", "")
        val nameVal = sharedPreferences.getString("nama", "")


        name.text = nameVal
        score.text = viewModel.score.value?.toString()
        updateNilai(username!!, score.text.toString().toInt())
        back.setOnClickListener {
            scoreDialog.dismiss()
            finish()
        }

        scoreDialog.show()
    }

    private fun updateNilai(username: String, nilai: Int) {
        if (checkNetworkConnection()) {
            databaseReference.child("users").child(username).child("skor").setValue(nilai)
            databaseReference.child("users").child("list_pengguna").child(username).child("skor")
                .setValue(nilai)

        } else {
            Toast.makeText(applicationContext, "Tidak ada koneksi internet", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun checkNetworkConnection(): Boolean {
        val connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}