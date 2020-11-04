package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.appclima.models.Ciudad

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad:TextView
    private lateinit var tvGrados:TextView
    private lateinit var tvStatus:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvStatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")
        val ciudadMex = Ciudad("Ciudad de México",15,"Soleado")
        val ciudadBerlin = Ciudad("Berlín", 30, "Cielo despejado")

        if(ciudad == "ciudad-mexico"){
            tvCiudad.text = ciudadMex.nombre
            tvGrados.text = ciudadMex.grados.toString() + "°"
            tvStatus.text = ciudadMex.estatus
        }else if(ciudad == "ciudad-berlin"){
            tvCiudad.text = ciudadBerlin.nombre
            tvGrados.text = ciudadBerlin.grados.toString() + "°"
            tvStatus.text = ciudadBerlin.estatus
        }else{
            Toast.makeText(this, "No se encuentra la información", Toast.LENGTH_SHORT).show()
        }
    }
}