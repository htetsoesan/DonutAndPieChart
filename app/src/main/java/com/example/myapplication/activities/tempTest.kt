package com.example.myapplication.activities

import java.sql.DriverManager.println
import kotlin.math.roundToInt


fun main() {
    val x = 10.55f

    val y: Int = x.roundToInt()
    println("y = $y")        // y = 11
}