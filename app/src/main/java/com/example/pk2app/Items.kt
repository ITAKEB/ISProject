package com.example.pk2app

import Data.DataDbHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.ui.ItemsAdapter
import com.example.pk2app.ui.PopUpAddItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Items.newInstance] factory method to
 * create an instance of this fragment.
 */
class Items : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var db: DataDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewItems)
        db = DataDbHelper(context)
        val dataItems = db.getData()



        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            recyclerView?.layoutManager = GridLayoutManager(activity, 2)
            // set the custom adapter to the RecyclerView
            var adapter = ItemsAdapter(dataItems)
            recyclerView?.adapter = adapter

            adapter.setOnItemClickListener(object : ItemsAdapter.onItemClickLister {
                override fun onItemClick(i: Int) {
                    Toast.makeText(activity, "You clicked on item no. $i", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        }

        val btAddNewItem = view?.findViewById<FloatingActionButton>(R.id.btaddItem)

        btAddNewItem?.setOnClickListener {
            PopUpAddItem(
                onSubmitClickListener = { item ->
                    Toast.makeText(activity, "Usted ingreso: ${item.getName()}", Toast.LENGTH_SHORT).show()
                    db.insertItem(item.getName(),item.getPrice(),item.getDescription())
                }
            ).show(parentFragmentManager,"dialog")
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Items.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Items().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}