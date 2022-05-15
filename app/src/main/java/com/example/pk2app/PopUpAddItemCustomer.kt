package com.example.pk2app

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton

class PopUpAddItemCustomer(
    private val onSubmitClickListener: (Float) -> Unit

):DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_add_item_customer, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val btClose = dialogView.findViewById<MaterialButton>(R.id.btClose)
        val btAdd = dialogView.findViewById<MaterialButton>(R.id.btAgregar)
        val btCancel = dialogView.findViewById<MaterialButton>(R.id.btCancelar)

        btAdd.setOnClickListener {
            onSubmitClickListener.invoke("1".toString().toFloat())
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