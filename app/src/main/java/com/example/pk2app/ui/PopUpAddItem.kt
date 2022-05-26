package com.example.pk2app.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.Person
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.pk2app.Item
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class PopUpAddItem(
    private val onSubmitClickListener: (Item) -> Unit,
    private val item:Item?

) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_add_item, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val btClose = dialogView.findViewById<MaterialButton>(R.id.btClose)
        val btAdd = dialogView.findViewById<MaterialButton>(R.id.btAgregar)
        val btCancel = dialogView.findViewById<MaterialButton>(R.id.btCancelar)
        val name = dialogView.findViewById<TextInputLayout>(R.id.txtItem)
        val price = dialogView.findViewById<TextInputLayout>(R.id.txtPrecio)
        val description = dialogView.findViewById<TextInputLayout>(R.id.txtInfo)


        if(item != null){
            name.editText?.setText(item.getName())
            description.editText?.setText(item.getDescription())
            price.editText?.setText(item.getPrice())
        }


        btAdd.setOnClickListener {
            onSubmitClickListener.invoke(
                Item(
                    0,
                    name.editText?.text.toString(),
                    price.editText?.text.toString(),
                    description.editText?.text.toString()
                )
            )
            dismiss()
        }


        btClose.setOnClickListener {
            dismiss()
        }

        btCancel.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog

    }

}