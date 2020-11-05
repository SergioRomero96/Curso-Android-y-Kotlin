package com.example.appclima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout

class CiudadesActivity : AppCompatActivity() {
    val TAG = "com.example.appclima.ciudades.CIUDAD"
    private lateinit var tvCountry: AutoCompleteTextView
    private lateinit var tvCity:TextInputLayout
    private lateinit var btnSearch:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        tvCountry = findViewById(R.id.country)
        tvCity = findViewById(R.id.city)
        btnSearch = findViewById(R.id.btn_search)

        val option:Array<String> = arrayOf("Mexico", "Berlin")
        val arrayAdapter = ArrayAdapter(this, R.layout.option_item, option)
        // to make default value
        //autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false)

        tvCountry.setAdapter(arrayAdapter)
        btnSearch.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            var value = ""
            println("ciudad: " + tvCountry.text.toString())
            if(tvCountry.text.toString() == "Mexico") {
                value = "3530597"
                intent.putExtra(TAG, value)
                startActivity(intent)
            }else if(tvCountry.text.toString() == "Berlin") {
                value = "2950159"
                intent.putExtra(TAG, value)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Seleccione un pais", Toast.LENGTH_SHORT).show()
            }

        })

    }
}