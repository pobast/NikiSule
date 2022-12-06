package com.example.nikisule

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.view.MotionEvent
import android.view.SurfaceHolder


class GamePanel(
    var thread: MainThread, var user: Player, var userPoint: Point, var foodManager: FoodManager
) : SurfaceHolder.Callback {

    private var gameOver = false
    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = MainThread(holder, this)
        thread.running = true
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.running = false
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    fun resetGame() {
        userPoint = Point(150, 150)
        user.update(userPoint)
        TODO()
        //foodManager = FoodManager()
    }

    @Override
    fun onTouchEvent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN ->
                //Reset the game if ended
                if (gameOver) {
                    resetGame()
                    gameOver = false
                }
            MotionEvent.ACTION_MOVE -> userPoint[event.x.toInt()] = event.y.toInt()
        }
        gameOver = true
    }

    @Override
    fun draw(canvas: Canvas){
        //super.draw(canvas)

        canvas.drawColor(Color.rgb(232, 200, 179))
        user.draw(canvas)
        //foodManager.draw(canvas)


    }

    public fun update(){}



}
