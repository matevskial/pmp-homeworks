package com.matevskial.pmphomeworks.homework2

import android.content.Context
import java.io.File
import java.io.PrintStream
import java.util.*

class StorageDictionary(private val context: Context) {
    private val fileName = "dictionary.txt"

    init {
        val dictionaryFile = File(context.filesDir.path + "/" + fileName)
        if(!dictionaryFile.exists()) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE)
        }
    }

    fun load(): MutableList<String> {
        val dictionaryEntries = ArrayList<String>()
        val reader = Scanner(context.openFileInput(fileName))

        reader.use {
            while(reader.hasNextLine()) {
                dictionaryEntries.add(reader.nextLine())
            }
        }

        return dictionaryEntries
    }

    fun add(entry: String) {
        val dictionaryEntries = load()
        dictionaryEntries.add(entry)
        saveToFile(dictionaryEntries)
    }

    fun update(oldEntry: String, newEntry: String) {
        val dictionaryEntries = load()
        val indexToModify = dictionaryEntries.indexOf(oldEntry)
        if(indexToModify != -1) {
            dictionaryEntries.set(indexToModify,newEntry)
            saveToFile(dictionaryEntries)
        }
    }

    fun delete(entry: String) {
        val dictionaryEntries = load()
        dictionaryEntries.remove(entry)
        saveToFile(dictionaryEntries)
    }

    private fun saveToFile(dictionaryEntries: List<String>) {
        val writer = PrintStream(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        writer.use {
            for(dictionaryEntry in dictionaryEntries) {
                writer.println(dictionaryEntry)
            }
        }
    }
}