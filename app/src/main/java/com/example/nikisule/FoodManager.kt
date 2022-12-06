package com.example.nikisule

import android.graphics.BitmapFactory
import java.util.*

class FoodManager {

    private val bf = BitmapFactory()
    private val FOOD_IMAGE1 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.burger)
    private val FOOD_IMAGE2 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.pizza)
    private val FOOD_IMAGE3 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.fries)
    private val FOOD_IMAGE4 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.hotdog)
    private val FOOD_IMAGE5 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.beer)
    private val FOOD_IMAGE6 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.sandwich)

    private val FOOD_IMAGE7 = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources,R.drawable.salad)


    private var foods //Array of fruits currently on screen
            : ArrayList<Food>? = null

    private var foodLocation //Location of falling food
            = 0
    private var playerSize //Size of the user's swipe space
            = 0
    private var foodHeight //Height of the food rectangle
            = 0
    private var color = 0

    private var score = 0 //Game score


    private val highScore = Constants.PREF!!.getInt("key", 0)

    private val misses = 0 //Number of food not eaten


    private var rand = Random()

    private var start: Long = 0
    private var initialization: Long = 0


    fun FruitManager(playerSize: Int, fruitLocation: Int, fruitHeight: Int, color: Int) {
        foods = ArrayList()
        foodLocation = fruitLocation
        this.playerSize = playerSize
        foodHeight = fruitHeight
        this.color = color
        initialization = System.currentTimeMillis()
        start = initialization

        //Add fruit to the array
        populateFood()
    }

    fun collisionDetection(player: Player): Boolean {

        //Check each fruit on screen for detection
        for (f in foods!!) {
            if (f.collisionDetection(player)) {

                //Game over, hit a bomb
                if (f.foodType == 6) {
                    return true
                }

                //Increment score
                score += f.foodType

                //Remove from the arraylist
                foods!!.remove(f)
            }
        }
        return false
    }

    private fun populateFood() {

        //Starting Y position for the fruit
        var currentY = -5 * Constants.SCREEN_HEIGHT / 4
        while (currentY < 0) {

            //Determine where horizontally to place the fruit
            val xStart = (Math.random() * (Constants.SCREEN_WIDTH - playerSize)).toInt()

            //Determines which type of fruit is spawned
            val type: Int = determineFoodType()

            //Add to the array list
            foods!!.add(Food(foodHeight, color, xStart, currentY, playerSize, type))
            currentY += foodHeight + foodLocation
        }
    }
    private fun determineFoodType(): Int {
        val `val`: Int = rand.nextInt(100 - 1) + 1
        var type = -1
        if (1 <= `val` && `val` <= 30) {
            type = 1
        } else if (30 < `val` && `val` <= 60) {
            type = 2
        } else if (60 < `val` && `val` <= 75) {
            type = 3
        } else if (75 < `val` && `val` <= 85) {
            type = 4
        } else if (85 < `val` && `val` <= 90) {
            type = 5
        } else if (90 < `val` && `val` <= 98) {
            type = 6
        } else if (`val` == 100 || `val` == 99) {
            type = 7
        }
        return type
    }

}