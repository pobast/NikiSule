package com.example.nikisule

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

open class Food(
    rectHeight: Int,
    val color: Int,
    startX: Int,
    startY: Int,
    playerSize: Int,
    val foodType: Int,
    val rect: Rect = Rect(startX, startY, startX + playerSize, startY + rectHeight),
) : GameObject {

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
        return Rect.intersects(rect, player.rect)
    }
}
