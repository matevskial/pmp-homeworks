package com.matevskial.pmphomeworks.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matevskial.pmphomeworks.R


/**
 * A simple [Fragment] subclass.
 * Represents a UI portion for dictionary entry
 */
class DictionaryEntryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary_entry, container, false)
    }

}