package com.example.appclima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class CiudadesActivity : AppCompatActivity() {
    val TAG = "com.example.appclima.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val buttonMexico = findViewById<Button>(R.id.btn_mexico)
        val buttonBerlin = findViewById<Button>(R.id.btn_berlin)

        buttonMexico.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this, "Ciudad de México",Toast.LENGTH_SHORT)
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(TAG, "3530597")
            startActivity(intent)
        })

        buttonBerlin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(TAG, "2950159")
            startActivity(intent)
        })
    }
}