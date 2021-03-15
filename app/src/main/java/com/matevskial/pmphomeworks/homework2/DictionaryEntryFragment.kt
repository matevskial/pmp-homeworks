package com.matevskial.pmphomeworks.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matevskial.pmphomeworks.R


/**
 * A simple [Fragment] subclass.
 * Represents a UI portion for dictionary entry
 */
class DictionaryEntryFragment : Fragment() {

    private lateinit var macedonianWordTextView: TextView
    private lateinit var englishWordTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dictionary_entry, container, false)

        macedonianWordTextView = view.findViewById(R.id.macedonianWordTextView)
        englishWordTextView = view.findViewById(R.id.englishWordTextView)

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val macedonianWord = requireArguments().getString("macedonianWord")
        val englishWord = requireArguments().getString("englishWord")

        macedonianWordTextView.text = macedonianWord
        englishWordTextView.text = englishWord

    }
}