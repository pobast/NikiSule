package com.example.nikisule

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class GamePanel(
    context:Context) : SurfaceView(context),SurfaceHolder.Callback {

    private var thread: MainThread? = null
    private var user: Player
    private var userPoint: Point
    private var foodManager: FoodManager


    init {
        //super(context)

        holder.addCallback(this)

        Constants.CURRENT_CONTEXT = context

        thread = MainThread(holder, this)

        //Instantiate player

        //Instantiate player
        user = Player(Rect(100, 100, 200, 200), Color.argb(0, 0, 0, 0))

        //Instantiate location of the player

        //Instantiate location of the player
        userPoint = Point(150, 150)
        user.update(userPoint)

        //Instantiate the fruit-managing class

        //Instantiate the fruit-managing class
        foodManager = FoodManager(200, 200, 325, Color.argb(0, 255, 255, 255))

        isFocusable = true
    }




    private var gameOver = false
    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = MainThread(holder, this)
        thread!!.running = true
        thread!!.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread!!.running = false
                thread!!.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    fun resetGame() {
        this.userPoint = Point(150, 150)
        user.update(userPoint)
        foodManager = FoodManager(200, 200, 325, Color.argb(0, 255, 255, 255))
    }




    override fun onTouchEvent(event: MotionEvent): Boolean {
        println("Hallo")
        when (event.action) {
            MotionEvent.ACTION_DOWN ->
                //Reset the game if ended
                if (gameOver) {
                    resetGame()
                    gameOver = false
                }
            MotionEvent.ACTION_MOVE -> userPoint[event.x.toInt()] = event.y.toInt()
        }
        return true
    }


    override fun draw(canvas: Canvas){
        super.draw(canvas)


        canvas.drawColor(Color.rgb(232, 200, 179))
        user.draw(canvas)
        foodManager.draw(canvas)


    }

    public fun update(){}



}
