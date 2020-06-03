package com.emkaydauda.colorturner

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private val selectedColors = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main)

        counterTextView.text = "Selecting color ${count + 1} of 5"

        colorSelector.addListener { color ->
            if (color != Color.TRANSPARENT) {
                if (count <= 5) {
                    selectedColors.add(color.toString())
                    showColors.isEnabled = true
                    count++
                    counterTextView.text = "Selecting color ${count + 1} of 5"
                }
                if (count == 5) {
                    startColorActivity()

                }
            } else {
                selectedColors.removeAt(selectedColors.lastIndex)
            }


        }

        showColors.setOnClickListener {
            startColorActivity()
        }


    }

    private fun startColorActivity() {
        if (timeout.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Enter an interval",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Prefs.putInt("time", timeout.text.toString().toInt())
            val a = selectedColors.toSet()
            Prefs.putStringSet("colors", a)
            count = 0
            selectedColors.clear()
            counterTextView.text = "Selecting color ${count + 1} of 5"
            showColors.isEnabled = false

            startActivity(Intent(this, ColorActivity::class.java))
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.timer_menu, menu)
//
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.one -> {
                Prefs.putInt("time", 1)
                true
            }
            R.id.three -> {
                Prefs.putInt("time", 3)
                true
            }
            R.id.five -> {
                Prefs.putInt("time", 5)
                true
            }

            R.id.ten -> {
                Prefs.putInt("time", 10)
                true
            }

            R.id.twenty -> {
                Prefs.putInt("time", 20)
                true
            }

            R.id.thirty -> {
                Prefs.putInt("time", 30)
                true
            }

            else -> true
        }
    }
}