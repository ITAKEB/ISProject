package com.example.pk2app

import Data.DataDbHelper
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pk2app.ui.AccountsAdapter
import com.example.pk2app.ui.ItemsAdapter
import com.example.pk2app.ui.PopUpAddCustomer
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

    private lateinit var db: DataDbHelper
    private lateinit var adapter: AccountsAdapter
    private var recyclerView: RecyclerView? = null

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

        recyclerView = view?.findViewById(R.id.recyclerViewAccounts)
        db = DataDbHelper(context)

        updateRecyclerView(recyclerView)

        val btAddItem = view?.findViewById<FloatingActionButton>(R.id.btaddItem)

        btAddItem?.setOnClickListener {
            PopUpAddCustomer(
                onSubmitClickListener = { board ->
                    Toast.makeText(activity, "Usted ingreso al cliente: ${board.getCustomer()}", Toast.LENGTH_SHORT).show()
                    db.insertBoard(board.getBoard(),board.getCustomer(),board.getTotalPrice())

                    updateRecyclerView(recyclerView)

                    adapter.notifyDataSetChanged()
                }
            ).show(parentFragmentManager,"dialog")
        }


    }

    private fun updateRecyclerView(recyclerView: RecyclerView?) {
        val dataBoards = db.getBoardData()

        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            recyclerView?.layoutManager = GridLayoutManager(activity, 2)
            // set the custom adapter to the RecyclerView
            adapter = AccountsAdapter(dataBoards)
            recyclerView?.adapter = adapter

            adapter.setOnItemClickListener(object : AccountsAdapter.onItemClickLister {
                override fun onItemClick(id: Int) {
                    val newActivity = Intent(activity, AccountView::class.java)
                    newActivity.putExtra("boardId",id)
                    newActivity.putExtra("payedAccount",0)
                    startActivity(newActivity)
                }
            })

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