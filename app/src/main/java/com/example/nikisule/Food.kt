package com.example.nikisule

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

class Food (private val color: Int, private val rect: Rect, private var score: Int, private val foodType:Int) : GameObject {


    override fun draw(canvas: Canvas?) {
        val paint = Paint()
        paint.color = color
        canvas?.drawRect(rect, paint)
    }

    override fun update() {}

    fun addYVal(y: Float) {
        rect.top += y.toInt()
        rect.bottom += y.toInt()
    }
    fun collisionDetection(player: Player): Boolean {
        return Rect.intersects(rect,player.rect)
    }
}
