package com.app.e_modulinteraktif

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.e_modulinteraktif.adapter.MainAdapter
import com.app.e_modulinteraktif.model.Menu
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {

    private lateinit var menuList: ArrayList<Menu>
    private lateinit var adapter: MainAdapter
    lateinit var logout: Button
    lateinit var dialog: Dialog
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var welcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        logout = findViewById(R.id.logout)
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_logout)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        menuList = ArrayList()
        adapter = MainAdapter(this, menuList)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMain)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = adapter

        init()

        logout.setOnClickListener {
            dialog.show()
            val tidak = dialog.findViewById<Button>(R.id.tidak)
            val iya = dialog.findViewById<Button>(R.id.iya)

            tidak.setOnClickListener { dialog.dismiss() }
            iya.setOnClickListener {
                editor.putString("login", "notlogin")
                editor.apply()
                dialog.dismiss()
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun init() {
        if (sharedPreferences.getString("level", "") == "pengguna") {
            menuList.add(Menu(2, R.drawable.square, "Materi", ""))
            menuList.add(Menu(1, R.drawable.book, "Contoh", ""))
            menuList.add(Menu(5, R.drawable.algebra, "Latihan", ""))
            adapter.notifyDataSetChanged()
        } else {
            menuList.add(Menu(8, R.drawable.book, "Daftar Pengguna", ""))
            menuList.add(Menu(9, R.drawable.square, "Daftar Skor", ""))
            menuList.add(Menu(10, R.drawable.maths, "Daftar Komentar", ""))

        }

    }

    inner class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
}