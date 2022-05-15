package com.example.pk2app

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton

class PopUpAddItemTable(
    private val onSubmitClickListener: (Float) -> Unit

) :DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_add_item_table, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val btClose = dialogView.findViewById<MaterialButton>(R.id.btClose)

        btClose.setOnClickListener {
            onSubmitClickListener.invoke("1".toString().toFloat())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog

    }
}