package com.example.pk2app.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton

class PopUpAreUSure(
    private val onSubmitClickListener: (Float) -> Unit
):DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_are_u_sure, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val btCont = dialogView.findViewById<MaterialButton>(R.id.btContinue)
        val btCancel = dialogView.findViewById<MaterialButton>(R.id.btCancel)

        btCont.setOnClickListener {
            onSubmitClickListener.invoke("1".toString().toFloat())
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