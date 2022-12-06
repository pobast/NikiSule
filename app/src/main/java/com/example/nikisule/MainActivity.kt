package com.example.nikisule

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Hello World! :)")


        //Fullscreen view, no toolbar/snackbar
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(metrics)

        //Set size of screen in Constants Class

        //Set size of screen in Constants Class
        Constants.SCREEN_WIDTH = metrics.widthPixels
        Constants.SCREEN_HEIGHT = metrics.heightPixels

        val pref = getSharedPreferences("myPrefsKey", MODE_PRIVATE)

        Constants.PREF = pref

        setContentView(GamePanel(this))


    }
}