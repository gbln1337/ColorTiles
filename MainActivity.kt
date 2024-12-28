package com.example.colortiles

import android.R.attr.x
import android.R.attr.y
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

// тип для координат
data class Coord(val x: Int, val y: Int)
class MainActivity : AppCompatActivity() {
    lateinit var tiles: Array<Array<View>>

    var isGameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //tiles = Array(4) { Array(4) { View(this) } }
        tiles = arrayOf(
            arrayOf(findViewById(R.id.t00), findViewById(R.id.t01), findViewById(R.id.t02), findViewById(R.id.t03)),
            arrayOf(findViewById(R.id.t10), findViewById(R.id.t11), findViewById(R.id.t12), findViewById(R.id.t13)),
            arrayOf(findViewById(R.id.t20), findViewById(R.id.t21), findViewById(R.id.t22), findViewById(R.id.t23)),
            arrayOf(findViewById(R.id.t30), findViewById(R.id.t31), findViewById(R.id.t32), findViewById(R.id.t33))
        )
        initField()
        // TODO: заполнить поле случайными цветами

    }

    fun getCoordFromString(s: String): Coord {
        // TODO: реализовать функцию, получающую из
        // строки вида "12" координаты Coord(1,2)
        // TODO: сообщать в лог координаты
        val x = s[0].toString().toInt()
        val y = s[1].toString().toInt()
        Log.d("Coords:", "($x, $y)")
        return Coord(x, y) 
    }
    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)
        val drawable = view.background as ColorDrawable
        if (drawable.color ==brightColor ) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    fun onClick(v: View) {
        if (isGameOver) return
        val coord = getCoordFromString(v.getTag().toString())


        for (i in 0..3) {
            changeColor(tiles[coord.x][i])
            changeColor(tiles[i][coord.y])
        }
        changeColor(v)
        checkVictory()
        // TODO: проверить раскраску поля на предмет победы
    }

    fun checkVictory() {
        // TODO: проверка победы
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)

        val firstTileColor = (tiles[0][0].background as ColorDrawable).color
        var isVictory = true
        for (row in tiles) {
            for (tile in row) {
                val tileColor = (tile.background as ColorDrawable).color
                if (tileColor != firstTileColor) {
                    isVictory = false
                    break
                }
            }
            if (!isVictory) break
        }

        if (isVictory) {
            Log.d("Victory", "Вы выиграли! Все плитки одного цвета.")
            isGameOver = true
        }
    }

    fun initField() {
        // TODO: заполнение поля случайными плитками (тёмный/светлый)
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)

        for (row in tiles) {
            for (tile in row) {
                val randomColor = if (Random.nextBoolean()) brightColor else darkColor
                tile.setBackgroundColor(randomColor)

            }
        }
    }
//fun initField() {
//    val brightColor = resources.getColor(R.color.bright)
//    val darkColor = resources.getColor(R.color.dark)
//
//    for (x in tiles.indices) {
//        for (y in tiles[x].indices) {
//            if (x == 2 || y == 2) {
//                tiles[x][y].setBackgroundColor(darkColor)
//            } else {
//                tiles[x][y].setBackgroundColor(brightColor)
//            }
//        }
//    }
//}


}