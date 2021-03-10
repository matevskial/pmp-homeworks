package com.matevskial.pmphomeworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    private lateinit var homeworkButtons: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeworkButtons = findViewById(R.id.homeworkButtons)
        setHomeworkButtonsClickHandlers()
    }

    private fun setHomeworkButtonsClickHandlers() {
        for((index, c) in homeworkButtons.children.withIndex()) {
            c.setOnClickListener {
                val packageName = this.packageName + ".homework" + (index + 1)
                val className = ".Homework" + (index + 1)
                val intent = Intent(this, Class.forName(packageName + className))
                startActivity(intent)
            }
        }
    }
}