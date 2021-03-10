package com.matevskial.pmphomeworks.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.children
import com.matevskial.pmphomeworks.R

class Homework1 : AppCompatActivity() {

    private lateinit var newTodoEditText: EditText
    private lateinit var newTodoButton: Button
    private lateinit var todoContainer: LinearLayout
    private lateinit var deleteTodoEntriesButton: Button
    private lateinit var editTextFilterTodos: EditText

    private lateinit var storage: StorageTodo
    private lateinit var todoList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework1)
        title = "Homework 1 - TODO app"

        newTodoEditText = findViewById(R.id.newTodoEditText)
        newTodoButton = findViewById(R.id.newTodoButton)
        todoContainer = findViewById(R.id.todoContainer)
        deleteTodoEntriesButton = findViewById(R.id.deleteTodoEntriesButton)
        editTextFilterTodos = findViewById(R.id.editTextFilterTodos)

        newTodoButton.setOnClickListener {
            createTodoEntry(newTodoEditText.text.toString())
            todoList.add(newTodoEditText.text.toString())
            storage.update(todoList)
            newTodoEditText.text.clear()

            Toast.makeText(this, "Added new TODO entry", Toast.LENGTH_SHORT).show()
        }

        deleteTodoEntriesButton.setOnClickListener {
            todoList.clear()
            todoContainer.removeAllViews()
            storage.update(todoList)
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

        storage = StorageTodo(this)
        loadTodoFromStorage()
    }

    private fun createTodoEntry(todoText: String) {
        val newTodoEntry = LayoutInflater.from(this).inflate(R.layout.todo_entry, todoContainer, false) as LinearLayout

        val todoTextView = newTodoEntry.getChildAt(0) as TextView
        todoTextView.text = todoText

        val todoDeleteButton = newTodoEntry.getChildAt(1)
        todoDeleteButton.setOnClickListener {
            todoList.remove(todoText)
            todoContainer.removeView(newTodoEntry)
            storage.update(todoList)
        }

        todoContainer.addView(newTodoEntry)
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

    private fun loadTodoFromStorage() {
        todoList = storage.load()
        for(todo in todoList) {
            createTodoEntry(todo)
        }
    }
}