package com.example.appclima

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appclima.models.Ciudad
import com.example.appclima.utils.NetworkUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad:TextView
    private lateinit var tvGrados:TextView
    private lateinit var tvStatus:TextView
    private lateinit var view:ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        supportActionBar?.hide()

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvStatus = findViewById(R.id.tvEstatus)
        view = findViewById(R.id.layout_detail)

        val city = intent.getSerializableExtra("city") as Ciudad
        if(city.main.temp > 20){
            view.setBackgroundColor(Color.parseColor("#a91836"))
            //window.statusBarColor = Color.parseColor("#a91836")

        }else{
            view.setBackgroundColor(Color.parseColor("#3d8ace"))
            //window.statusBarColor = Color.parseColor("#3d8ace")
        }
        tvCiudad.text = city.name
        tvGrados.text = city.main?.temp.toString()
        tvEstatus.text = city.weather?.get(0)?.description

    }


}