package com.example.nikisule

import android.content.Context
import android.content.SharedPreferences

object Constants {
    var SCREEN_WIDTH //Width of the phone screen
            = 0
    var SCREEN_HEIGHT //Height of the phone screen
            = 0
    var CURRENT_CONTEXT //Context of the game
            : Context? = null
    var PREF: SharedPreferences? = null
}