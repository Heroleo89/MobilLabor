package com.example.mobillab.ui.characters

import android.app.AlertDialog
import android.widget.EditText

class SearchDialog(
    val fragment: CharactersFragment,
    val action: (String) -> Unit,
) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
    private var inputFiled = EditText(fragment.requireContext())

    init {
        builder.setTitle("Add character")
        setUpButtons()
    }

    fun showDialog() {
        inputFiled = EditText(fragment.requireContext())

        builder.setView(inputFiled)
        builder.show()
    }

    private fun setUpButtons() {

        builder.setPositiveButton("OK") { _, _ -> action(inputFiled.text.toString()) }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
    }
}