package com.example.nikisule

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect


abstract class Player(val rect: Rect, val color:Int) : GameObject {



    open fun getRectangle(): Rect? {
        return rect
    }

    override fun draw(canvas: Canvas?) {
        val paint = Paint()
        paint.color = color
        canvas?.drawRect(rect, paint)
    }

    override fun update() {}

    open fun update(point: Point) {

        //Set new location of the user
        rect.set(
            point.x - rect.width() / 2, point.y - rect.height() / 2,
            point.x + rect.width() / 2, point.y + rect.height() / 2
        )
    }

}

