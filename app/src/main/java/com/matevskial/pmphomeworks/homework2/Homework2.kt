package com.matevskial.pmphomeworks.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matevskial.pmphomeworks.R

class Homework2 : AppCompatActivity() {

    private lateinit var searchDictionaryEditText: EditText
    private lateinit var addToDictionaryButton: Button
    private lateinit var searchDictionaryButton: Button
    private lateinit var dictionaryEntriesRecycleView: RecyclerView

    lateinit var storage: StorageDictionary
    private lateinit var dictionaryEntries: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework2)
        title = "Homework 2 - Dictionary"

        addToDictionaryButton = findViewById(R.id.addToDictionaryButton)
        searchDictionaryEditText = findViewById(R.id.searchDictionaryEditText)
        searchDictionaryButton = findViewById(R.id.searchDictionaryButton)
        dictionaryEntriesRecycleView = findViewById(R.id.dictionaryEntriesRecycleView)

        addToDictionaryButton.setOnClickListener {
            AddToDictionaryDialogFragment().show(supportFragmentManager, null)
        }

        searchDictionaryButton.setOnClickListener {
            (dictionaryEntriesRecycleView.adapter as DictionaryEntriesRecycleViewAdapter).filter(getFilterQuery())
        }

        dictionaryEntries = ArrayList()
        storage = StorageDictionary(this, dictionaryEntries)
        storage.load()

        dictionaryEntriesRecycleView.adapter = DictionaryEntriesRecycleViewAdapter(this)
        dictionaryEntriesRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    fun addToDictionary(entry: String) {
        storage.add(entry)
        (dictionaryEntriesRecycleView.adapter as DictionaryEntriesRecycleViewAdapter).filter(getFilterQuery())
    }

    fun deleteFromDictionary(position: Int) {
        storage.delete(position)
        (dictionaryEntriesRecycleView.adapter as DictionaryEntriesRecycleViewAdapter).filter(getFilterQuery())
    }

    fun updateDictionary(position: Int, newEntry: String) {
        storage.update(position, newEntry)
        (dictionaryEntriesRecycleView.adapter as DictionaryEntriesRecycleViewAdapter).filter(getFilterQuery())
    }

    fun getDictionaryEntries(): List<String> {
        return storage.dictionaryEntries
    }

    fun getFilterQuery(): String {
        return searchDictionaryEditText.text.toString()
    }
}