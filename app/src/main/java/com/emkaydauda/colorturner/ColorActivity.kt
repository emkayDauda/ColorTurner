package com.emkaydauda.colorturner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_color.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val colors = Prefs.getStringSet("colors", emptySet())
        val colorsArray = colors.toList()
        val count = colorsArray.size
        var position = 0;
        val timeOut = Prefs.getInt("time", 5)
        base.setBackgroundColor(colorsArray[0].toInt())

        println(position)
        println(colorsArray)


        Timer().scheduleAtFixedRate(0 * 1000L, timeOut * 1000L) {
            base.setBackgroundColor(colorsArray[position].toInt())
            position++
            if (position == count) position = 0
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}