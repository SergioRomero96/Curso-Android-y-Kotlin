package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), CompletadoListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonValidarRed = findViewById<Button>(R.id.btnValidarRed)
        val buttonSolicitudHTTP = findViewById<Button>(R.id.btnSolicitud)

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
    }

    override fun descargaCompleta(resultado: String) {
        Log.d("descargaCompleta", resultado)
    }


}