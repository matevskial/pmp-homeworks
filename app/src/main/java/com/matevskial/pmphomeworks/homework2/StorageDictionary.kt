package com.matevskial.pmphomeworks.homework2

import android.content.Context
import java.io.File
import java.io.PrintStream
import java.util.*

class StorageDictionary(private val context: Context, val dictionaryEntries: MutableList<String>) {
    private val fileName = "dictionary.txt"

    init {
        val dictionaryFile = File(context.filesDir.path + "/" + fileName)
        if(!dictionaryFile.exists()) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
        }
    }

    fun load() {
        dictionaryEntries.clear()
        val reader = Scanner(context.openFileInput(fileName))

        reader.use {
            while(reader.hasNextLine()) {
                dictionaryEntries.add(reader.nextLine())
            }
        }
    }

    fun add(entry: String) {
        dictionaryEntries.add(entry)
        saveToFile()
    }

    fun update(position: Int, newEntry: String) {
        dictionaryEntries[position] = newEntry
        saveToFile()
    }

    fun delete(position: Int) {
        dictionaryEntries.removeAt(position)
        saveToFile()
    }

    private fun saveToFile() {
        val writer = PrintStream(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        writer.use {
            for(dictionaryEntry in dictionaryEntries) {
                writer.println(dictionaryEntry)
            }
        }
    }
}