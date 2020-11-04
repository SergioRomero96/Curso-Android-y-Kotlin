package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonValidarRed = findViewById<Button>(R.id.btnValidarRed)
        val buttonSolicitudHTTP = findViewById<Button>(R.id.btnSolicitud)
        val buttonVolleyHTTP = findViewById<Button>(R.id.btnSolicitudVolley)
        val buttonSolicitudOkHTTP = findViewById<Button>(R.id.btnSolicitudOk)

        buttonValidarRed.setOnClickListener {
            //Codigo para validar red
            if (Network.hayRed(this)){
                Toast.makeText(this, "Si hay red!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No hay una conexion a internet!", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSolicitudHTTP.setOnClickListener {
            if(Network.hayRed(this)){
                //Log.d("buttonSolicitudHTTP", descargarDatos("https://www.google.com"))
                DescargaURL(this).execute("https://www.google.com")
            }else{
                Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show()
            }
        }

        buttonVolleyHTTP.setOnClickListener {
            if(Network.hayRed(this)){
                solicitudHTTPVolley("https://www.google.com")
            }else{
                Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSolicitudOkHTTP.setOnClickListener {
            if(Network.hayRed(this)){
                solicitudHTTPOkHTTP("https://www.google.com")
            }else{
                Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun descargaCompleta(resultado: String) {
        Log.d("descargaCompleta", resultado)
    }

    //Metodo para volley
    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try {
                Log.d("SolicitudHTTPVolley", response)
            }catch (e: Exception){

            }
        }, Response.ErrorListener {  })

        queue.add(solicitud)
    }

    //Metodo para OkHTP
    private fun solicitudHTTPOkHTTP(url:String){
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()

        cliente.newCall(solicitud).enqueue((object: okhttp3.Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                // implementar error
            }

            override fun onResponse(call: Call?, response: okhttp3.Response) {
                val result = response.body().string()

                this@MainActivity.runOnUiThread{
                    try {
                        Log.d("solicitudHTTPOkHTTP", result)
                    }catch (e: Exception){

                    }
                }
            }
        }))
    }

}