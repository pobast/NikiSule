package com.example.nikisule

import android.graphics.Canvas
import android.view.SurfaceHolder

open class MainThread(private val surfaceHolder: SurfaceHolder, private val gamePanel: GamePanel) :
    Thread() {
    var running: Boolean = false
    val MAXFPS = 30

    private var avgFPS = 0.0
    private var canvas: Canvas? = null
    override fun run() {
        var startTime: Long
        var time: Long
        var waitTime: Long
        var frameCount = 0
        var totalTime: Long = 0
        val targetTime: Long = (1000 / MAXFPS.toLong())
        while (running) {
            startTime = System.nanoTime()
            canvas = null
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    gamePanel.update()
                    gamePanel.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            time = System.nanoTime() - startTime / 1000000
            waitTime = targetTime - time
            try {
                if (waitTime > 0) {
                    sleep(waitTime)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            totalTime += System.nanoTime() - startTime
            frameCount++
            if (frameCount == MAXFPS) {
                avgFPS = (1000 / (totalTime / frameCount / 1000000)).toDouble()
                frameCount = 0
                totalTime = 0
                println(avgFPS)
            }
        }
    }

}