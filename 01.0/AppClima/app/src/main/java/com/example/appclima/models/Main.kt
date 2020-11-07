package com.example.appclima.models

import java.io.Serializable

class Main(temp: Double): Serializable {
    var temp:Double = 0.0

    init{
        this.temp = temp
    }
}