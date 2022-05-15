package com.example.pk2app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AccountsAdapter.ViewHolder>? = null

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewAccounts)


        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            recyclerView?.layoutManager = GridLayoutManager(activity, 2)
            // set the custom adapter to the RecyclerView
            var adapter = AccountsAdapter()
            recyclerView?.adapter = adapter

            adapter.setOnItemClickListener(object : AccountsAdapter.onItemClickLister{

                override fun onItemClick(i: Int) {
                    Toast.makeText(activity,"You clicked on item no. $i",Toast.LENGTH_SHORT).show()
                    val newActivity = Intent(activity, AccountView::class.java)
                    startActivity(newActivity)
                    //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

            })

         }

        val btAddItem = view?.findViewById<FloatingActionButton>(R.id.btaddItem)

        btAddItem?.setOnClickListener {
            PopUpAddCustomer(
                onSubmitClickListener = { quantity ->
                    Toast.makeText(activity, "Usted ingreso: $quantity", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}