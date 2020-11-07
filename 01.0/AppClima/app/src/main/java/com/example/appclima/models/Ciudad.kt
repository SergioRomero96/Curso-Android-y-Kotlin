package com.example.appclima.models

import java.io.Serializable

class Ciudad(name:String, weather:ArrayList<Weather>, main:Main):Serializable {
    var name: String
    var weather:ArrayList<Weather>
    var main:Main

    init {
        this.name = name
        this.weather = weather
        this.main = main
    }
}