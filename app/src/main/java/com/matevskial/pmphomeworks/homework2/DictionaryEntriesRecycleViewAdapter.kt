package com.matevskial.pmphomeworks.homework2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.matevskial.pmphomeworks.R

class DictionaryEntriesRecycleViewAdapter(private val context: Homework2)
    : RecyclerView.Adapter<DictionaryEntriesRecycleViewAdapter.ViewHolder>() {

    private lateinit var filteredDictionaryEntries: ArrayList<String>

    init {
        filter(context.getFilterQuery())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val macedonianWordTextView: TextView = view.findViewById(R.id.macedonianWordTextView)
        private val englishWordTextView: TextView = view.findViewById(R.id.englishWordTextView)

        val deleteEntryButton: Button = view.findViewById(R.id.deleteEntryButton)
        val openEditEntryDialogButton: Button = view.findViewById(R.id.openEditEntryDialogButton)

        fun setMacedonianWord(macedonianWord: String) {
            macedonianWordTextView.text = macedonianWord
        }

        fun setEnglishWord(englishWord: String) {
            englishWordTextView.text = englishWord
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_view_dictionary_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parts = filteredDictionaryEntries[position].split("\t")
        holder.setMacedonianWord(parts[0])
        holder.setEnglishWord(parts[1])

        holder.deleteEntryButton.setOnClickListener {
            context.deleteFromDictionary(position)
        }

        holder.openEditEntryDialogButton.setOnClickListener {
            val bundle = bundleOf("isEdit" to true, "position" to position,
                "macedonianWord" to parts[0],
                "englishWord" to parts[1])
            val dialog = AddOrEditDictionaryEntryDialogFragment()
            dialog.arguments = bundle
            dialog.show(context.supportFragmentManager, null)
        }
    }

    override fun getItemCount(): Int {
        return filteredDictionaryEntries.size
    }

    fun filter(query: String) {
        filteredDictionaryEntries = ArrayList()
        for (entry in context.getDictionaryEntries()) {
            if (entry.isBlank() || entry.contains(query)) {
                filteredDictionaryEntries.add(entry)
            }
        }
        notifyDataSetChanged()
    }
}