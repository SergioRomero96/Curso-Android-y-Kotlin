package com.example.appclima.models

import java.io.Serializable

class Weather(description:String): Serializable {
    var description:String = ""

    init {
        this.description = description
    }
}