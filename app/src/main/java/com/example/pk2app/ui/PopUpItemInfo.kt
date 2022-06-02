package com.example.pk2app.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.pk2app.Item
import com.example.pk2app.ItemBoard
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class PopUpItemInfo (

    val itemBoard: ItemBoard

    ): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_item_info, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val name = dialogView.findViewById<TextInputLayout>(R.id.txtItem)
        val description = dialogView.findViewById<TextInputLayout>(R.id.txtDescripcion)
        val price = dialogView.findViewById<TextInputLayout>(R.id.txtPrecio)
        val btClose = dialogView.findViewById<MaterialButton>(R.id.btClose)

        name.editText?.setText(itemBoard.getItemTitle())
        description.editText?.setText(itemBoard.getItemDescription())
        price.editText?.setText(itemBoard.getItemPrice().toString())

        btClose.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}