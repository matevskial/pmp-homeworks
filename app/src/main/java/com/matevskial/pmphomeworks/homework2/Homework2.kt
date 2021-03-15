package com.matevskial.pmphomeworks.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.children
import com.matevskial.pmphomeworks.R

class Homework2 : AppCompatActivity() {

    private lateinit var dictionaryEntriesScrollView: ScrollView
    private lateinit var dictionaryEntriesLinearLayout: LinearLayout
    private lateinit var searchDictionaryEditText: EditText
    private lateinit var addToDictionaryButton: Button
    private lateinit var searchDictionaryButton: Button

    private lateinit var currentEditedDictionaryEntryView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework2)
        title = "Homework 2 - Dictionary"

        dictionaryEntriesScrollView = findViewById(R.id.dictionaryEntriesScrollView)
        addToDictionaryButton = findViewById(R.id.addToDictionaryButton)
        dictionaryEntriesLinearLayout = findViewById(R.id.dictionaryEntriesLinearLayout)
        searchDictionaryEditText = findViewById(R.id.searchDictionaryEditText)
        searchDictionaryButton = findViewById(R.id.searchDictionaryButton)

        addToDictionaryButton.setOnClickListener {
            AddToDictionaryDialogFragment().show(supportFragmentManager, null)
        }

        searchDictionaryButton.setOnClickListener {
            filterDictionaryEntries()
        }
    }

    private fun filterDictionaryEntries() {
        val searchText = searchDictionaryEditText.text
        for(c in dictionaryEntriesLinearLayout.children) {
            val macedonianWordTextView = c.findViewById<TextView>(R.id.macedonianWordTextView)
            val englishWordTextView = c.findViewById<TextView>(R.id.englishWordTextView)

            if (searchText.isEmpty() || macedonianWordTextView.text.contains(searchText) ||
                    englishWordTextView.text.contains(searchText)) {
                c.visibility = View.VISIBLE
            } else {
                c.visibility = View.GONE
            }
        }
    }

    fun addToDictionary(macedonianWord: String, englishWord: String) {
        val newDictionaryEntry = LayoutInflater.from(this).inflate(R.layout.fragment_dictionary_entry, dictionaryEntriesLinearLayout, false)

        val macedonianWordTextView = newDictionaryEntry.findViewById<TextView>(R.id.macedonianWordTextView)
        val englishWordTextView = newDictionaryEntry.findViewById<TextView>(R.id.englishWordTextView)
        val deleteEntryButton = newDictionaryEntry.findViewById<Button>(R.id.deleteEntryButton)
        val openEditEntryDialogButton = newDictionaryEntry.findViewById<Button>(R.id.openEditEntryDialogButton)

        macedonianWordTextView.text = macedonianWord
        englishWordTextView.text = englishWord

        openEditEntryDialogButton.setOnClickListener {
            val bundle = bundleOf("isEdit" to true,
                    "macedonianWord" to macedonianWordTextView.text,
                    "englishWord" to englishWordTextView.text)

            currentEditedDictionaryEntryView = newDictionaryEntry

            val dialog = AddToDictionaryDialogFragment()
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, null)
        }

        deleteEntryButton.setOnClickListener {
            dictionaryEntriesLinearLayout.removeView(newDictionaryEntry)
        }

        dictionaryEntriesLinearLayout.addView(newDictionaryEntry)
    }

    fun editDictionaryEntry(macedonianWord: String, englishWord: String) {
        val macedonianWordTextView = currentEditedDictionaryEntryView.findViewById<TextView>(R.id.macedonianWordTextView)
        val englishWordTextView = currentEditedDictionaryEntryView.findViewById<TextView>(R.id.englishWordTextView)

        macedonianWordTextView.text = macedonianWord
        englishWordTextView.text = englishWord
    }
}