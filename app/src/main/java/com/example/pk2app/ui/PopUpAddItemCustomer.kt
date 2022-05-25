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

class PopUpAddItemCustomer(
    private val onSubmitClickListener: (ItemBoard) -> Unit

) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_add_item_customer, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val btClose = dialogView.findViewById<MaterialButton>(R.id.btClose)
        val btAdd = dialogView.findViewById<MaterialButton>(R.id.btAgregar)
        val btCancel = dialogView.findViewById<MaterialButton>(R.id.btCancelar)
        val itemTitle = dialogView.findViewById<TextInputLayout>(R.id.txtItem)
        val itemQuantity = dialogView.findViewById<TextInputLayout>(R.id.txtCantidad)
        val itemPrice = dialogView.findViewById<TextInputLayout>(R.id.txtDescripcion)


        btAdd.setOnClickListener {
            onSubmitClickListener.invoke(
                ItemBoard(
                    0,
                    0,
                    itemTitle.editText?.text.toString(),
                    itemPrice.editText?.text.toString().toLong(),
                    itemPrice.editText?.text.toString().toLong(),
                    itemQuantity.editText?.text.toString().toInt()
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