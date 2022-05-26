package com.example.pk2app.ui

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.DialogFragment
import com.example.pk2app.Item
import com.example.pk2app.ItemBoard
import com.example.pk2app.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class PopUpAddItemCustomer(
    private val onSubmitClickListener: (ItemBoard) -> Unit,
    private val items:MutableList<Item>

) : DialogFragment(), AdapterView.OnItemClickListener {

    private var itemPrice:TextInputLayout? = null

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
        itemPrice = dialogView.findViewById(R.id.txtDescripcion)
        val autoComplete = dialogView.findViewById<AutoCompleteTextView>(R.id.autoTextView)

        val dropdownAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.list_item,
            items
        )

        with(autoComplete) {
            setAdapter(dropdownAdapter)

            onItemClickListener = this@PopUpAddItemCustomer




        }




        btAdd.setOnClickListener {
            onSubmitClickListener.invoke(
                ItemBoard(
                    0,
                    0,
                    itemTitle.editText?.text.toString(),
                    itemPrice?.editText?.text.toString().toLong(),
                    itemPrice?.editText?.text.toString().toLong(),
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        itemPrice?.editText?.setText(items[id.toInt()].getPrice())
    }
}