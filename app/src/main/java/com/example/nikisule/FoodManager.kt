package com.example.nikisule

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

class FoodManager(
    playerSize: Int, foodLocation: Int, foodHeight: Int, color: Int,

) {

    init {

        //foods = ArrayList()



        var start : Long = System.currentTimeMillis()

        //Add fruit to the array
        populateFood()
    }

    private var foods= ArrayList<Food>()
    private val bf = BitmapFactory()
    private val FOOD_IMAGE1 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.burger)
    private val FOOD_IMAGE2 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.pizza)
    private val FOOD_IMAGE3 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.fries)
    private val FOOD_IMAGE4 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.hotdog)
    private val FOOD_IMAGE5 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.beer)
    private val FOOD_IMAGE6 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.salad)

    private val FOOD_IMAGE7 =
        BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT!!.resources, R.drawable.sandwich)

    //private var foods : ArrayList<Food> = ArrayList()

    private var foodLocation //Location of falling food
            = 0
    private var playerSize //Size of the user's swipe space
            = 0
    private var foodHeight //Height of the food rectangle
            = 0
    private var color = 0

    private var score = 0 //Game score


    private val highScore = Constants.PREF!!.getInt("key", 0)

    private var misses = 0 //Number of food not eaten


    private var rand = Random()

    private var start: Long = 0
    private var initialization: Long = 0



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
            println("foods.size")
            foods.add(Food(foodHeight, color, xStart, currentY, playerSize, type))
            currentY += foodHeight + foodLocation
        }
    }

    private fun determineFoodType(): Int {
        println("Hallo")
        val `val2`: Int = 20
        //val `val`: Int = rand.nextInt(100 - 1) + 1
        println(`val2`)
        var type = -1
        if (1 <= `val2` && `val2` <= 30) {
            type = 1
        } else if (30 < `val2` && `val2` <= 60) {
            type = 2
        } else if (60 < `val2` && `val2` <= 75) {
            type = 3
        } else if (75 < `val2` && `val2` <= 85) {
            type = 4
        } else if (85 < `val2` && `val2` <= 90) {
            type = 5
        } else if (90 < `val2` && `val2` <= 98) {
            type = 6
        } else if (`val2` == 100 || `val2` == 99) {
            type = 7
        }
        return type
    }

    fun update(): Boolean {
        val timeElapsed = (System.currentTimeMillis() - start).toInt()
        start = System.currentTimeMillis()

        //Determine fall speed of the fruit
        //Value gets larger as the game progresses
        val speed = Math.sqrt(1 + (start - initialization) / 1000.0)
            .toFloat() * Constants.SCREEN_HEIGHT / 10000.0f

        //Add y value to each fruit on the screen
        for (fruit in foods!!) {
            fruit.addYVal(speed * timeElapsed)
        }

        //Fruit has made it to the bottom of the screen, add a strike to the count
        if (foods!!.get(foods!!.size).rect.top >= Constants.SCREEN_HEIGHT) {


            //Fruit isn't a bomb, mark a penalty
            if (foods!!.get(foods!!.size).foodType !== 6) {
                misses++
            }

            //Game Over
            if (misses == 3) {
                return true
            }

            //Remove from the array list
            foods!!.remove(foods!!.get(foods!!.size))
        }

        //Add a new fruit to be spawned
        val type: Int = determineFoodType()
        val xStart = (Math.random() * (Constants.SCREEN_WIDTH - playerSize)).toInt()
        foods!!.add(
            0, Food(
                foodHeight,
                color,
                xStart,
                foods!!.get(0).rect.top - foodHeight - foodLocation,
                playerSize,
                type
            )
        )
        return false
    }

    fun draw(canvas: Canvas){
        var photo : Bitmap
        photo = FOOD_IMAGE1

        for (food in foods!!) {
            food.draw(canvas)
            when (food.foodType) {
                1 -> photo = FOOD_IMAGE1
                2 -> photo = FOOD_IMAGE2
                3 -> photo = FOOD_IMAGE3
                4 -> photo = FOOD_IMAGE4
                5 -> photo = FOOD_IMAGE5
                6 -> photo = FOOD_IMAGE6
                7 -> photo = FOOD_IMAGE7

            }
            canvas.drawBitmap(photo, null, food.rect, Paint())
        }
    }



}