package com.example.solicitudeshttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonValidarRed = findViewById<Button>(R.id.btnValidarRed)

        buttonValidarRed.setOnClickListener {
            //Codigo para validar red
            if (Network.hayRed(this)){
                Toast.makeText(this, "Si hay red!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No hay una conexion a internet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}