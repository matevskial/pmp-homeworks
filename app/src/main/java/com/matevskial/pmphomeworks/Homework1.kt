package com.matevskial.pmphomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.children

class Homework1 : AppCompatActivity() {

    private lateinit var newTodoEditText: EditText
    private lateinit var newTodoButton: Button
    private lateinit var todoContainer: LinearLayout
    private lateinit var deleteTodoEntriesButton: Button
    private lateinit var editTextFilterTodos: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework1)
        title = "Homework 1"

        newTodoEditText = findViewById(R.id.newTodoEditText)
        newTodoButton = findViewById(R.id.newTodoButton)
        todoContainer = findViewById(R.id.todoContainer)
        deleteTodoEntriesButton = findViewById(R.id.deleteTodoEntriesButton)
        editTextFilterTodos = findViewById(R.id.editTextFilterTodos)

        newTodoButton.setOnClickListener {
            val todoEntry = createTodoEntry()
            todoContainer.addView(todoEntry)
            newTodoEditText.text.clear()

            Toast.makeText(this, "Added new TODO entry", Toast.LENGTH_SHORT).show()
        }

        deleteTodoEntriesButton.setOnClickListener {
            todoContainer.removeAllViews()
        }

        editTextFilterTodos.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                filterTodoEntries(text.toString())
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    private fun createTodoEntry() : LinearLayout {
        val newTodoEntry = LayoutInflater.from(this).inflate(R.layout.todo_entry, null) as LinearLayout

        val todoTextView = newTodoEntry.getChildAt(0) as TextView
        todoTextView.text = newTodoEditText.text

        val todoDeleteButton = newTodoEntry.getChildAt(1)
        todoDeleteButton.setOnClickListener {
            todoContainer.removeView(newTodoEntry)
        }

        return newTodoEntry
    }

    private fun filterTodoEntries(text: String) {
        for (c in todoContainer.children) {
            val todoEntry = c as LinearLayout
            val todoTextView = todoEntry.getChildAt(0) as TextView
            if(text.isEmpty() || todoTextView.text.contains(text)) {
                todoEntry.visibility = View.VISIBLE
            } else {
                todoEntry.visibility = View.GONE
            }
        }
    }
}