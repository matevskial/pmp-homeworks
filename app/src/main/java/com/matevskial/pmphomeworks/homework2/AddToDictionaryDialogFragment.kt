package com.matevskial.pmphomeworks.homework2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.matevskial.pmphomeworks.R

class AddToDictionaryDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.dialog_add_to_dictionary, null)
            val macedonianWordEditText = view.findViewById<EditText>(R.id.macedonianWordEditText)
            val englishWordEditText = view.findViewById<EditText>(R.id.englishWordEditText)

            val bundle = arguments

            val isEdit = bundle?.getBoolean("isEdit")

            if(isEdit == true) {
                macedonianWordEditText.setText(bundle.getString("macedonianWord"), TextView.BufferType.EDITABLE)
                englishWordEditText.setText(bundle.getString("englishWord"), TextView.BufferType.EDITABLE)
            }

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                .setPositiveButton("Save") { dialog, id ->
                    val macedonianWord = macedonianWordEditText.text.toString()
                    val englishWord = englishWordEditText.text.toString()
                    if (isEdit == true) {
                        (it as Homework2).editDictionaryEntry(macedonianWord, englishWord)
                    } else {
                        (it as Homework2).addToDictionary(macedonianWord, englishWord)
                    }

                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}