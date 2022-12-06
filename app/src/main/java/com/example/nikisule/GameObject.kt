package com.example.nikisule

import android.graphics.Canvas

interface GameObject {
    fun draw(canvas: Canvas?)
    fun update()
}