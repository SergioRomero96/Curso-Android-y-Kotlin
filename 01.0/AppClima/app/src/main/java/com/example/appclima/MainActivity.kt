package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appclima.models.Ciudad
import com.example.appclima.utils.NetworkUtil
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad:TextView
    private lateinit var tvGrados:TextView
    private lateinit var tvStatus:TextView
    private val TOKEN = "d0d0109df66906f06c3b5fe58638cdbd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvStatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")

        if(NetworkUtil.hasNetwork(this)){
            requestHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&appid="+TOKEN+"&units=metric&lang=es")
        }else{
            Toast.makeText(this, "No hay internet!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun requestHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> {
            response ->
            try {
                Log.d("Response:", response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                tvCiudad.text = ciudad.name
                tvGrados.text = ciudad.main?.temp.toString() + "°"
                tvStatus.text = ciudad.weather?.get(0)?.description
            }catch (e: Exception){
            }
        }, Response.ErrorListener {
            Toast.makeText(this, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show()
        })

        queue.add(request)
    }
}