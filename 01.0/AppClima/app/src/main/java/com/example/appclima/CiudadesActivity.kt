package com.example.appclima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appclima.models.Ciudad
import com.example.appclima.utils.NetworkUtil
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson

class CiudadesActivity : AppCompatActivity() {
    private val TOKEN = "d0d0109df66906f06c3b5fe58638cdbd"
    private val URL = "https://api.openweathermap.org/data/2.5/weather"
    private lateinit var tvCountry: AutoCompleteTextView
    private lateinit var tvCity: TextInputLayout
    private lateinit var btnSearch: Button
    private lateinit var view:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        tvCountry = findViewById(R.id.country)
        tvCity = findViewById(R.id.city)
        btnSearch = findViewById(R.id.btn_search)
        view = findViewById(R.id.layout_city)

        val option: Array<String> = arrayOf("Mexico", "Berlin")
        val arrayAdapter = ArrayAdapter(this, R.layout.option_item, option)
        // to make default value
        //autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false)

        tvCountry.setAdapter(arrayAdapter)
        btnSearch.setOnClickListener(View.OnClickListener {
            val city = tvCity.editText?.text.toString()
            if(validateCity()){
                if(NetworkUtil.hasNetwork(this)){
                    requestHTTP(this.URL+"?q="+city+"&appid="+TOKEN+"&units=metric&lang=es")
                }else{
                    Toast.makeText(this, "No hay internet!", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun requestHTTP(url: String) {
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            Log.d("response", response)
            val gson = Gson()
            val city = gson.fromJson(response, Ciudad::class.java)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", city)
            startActivity(intent)

        }, Response.ErrorListener { error ->
            Log.d("error", error.toString())
            val snackbar = Snackbar.make(view,"Error al conectar con el servidor", Snackbar.LENGTH_SHORT)
            snackbar.show()
        })
        queue.add(request)
    }

    private fun validateCity(): Boolean{
        val city = tvCity.editText?.text.toString()
        if(city.isEmpty()){
            tvCity.error = "Ciudad es requerido"
            return false
        }else{
            tvCity.error = null
            return true
        }
    }
}