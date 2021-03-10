package com.matevskial.pmphomeworks.homework1

import android.content.Context
import java.io.*
import java.util.*

class StorageTodo(private val context: Context) {
    private val fileName = "todo.txt"

    init {
        val todoFile = File(context.filesDir.path + "/" + fileName)
        if(!todoFile.exists()) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
        }
    }

    fun load(): MutableList<String> {
        val todoList = ArrayList<String>()
        val reader = Scanner(context.openFileInput(fileName))

        reader.use {
            while(reader.hasNextLine()) {
                todoList.add(reader.nextLine())
            }
        }

        return todoList
    }

    fun update(todoList: List<String>) {
        val writer = PrintStream(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        writer.use {
            for(todo in todoList) {
                writer.println(todo)
            }
        }
    }
}