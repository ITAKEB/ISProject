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
import com.example.pk2app.ui.AccountsPayedAdapter
import com.example.pk2app.ui.PopUpAreUSure
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PayedAccounts.newInstance] factory method to
 * create an instance of this fragment.
 */
class PayedAccounts : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var db: DataDbHelper
    private lateinit var adapter: AccountsPayedAdapter
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
        return inflater.inflate(R.layout.fragment_payed_accounts, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recyclerView = view?.findViewById(R.id.recyclerViewAccountsPayed)
        db = DataDbHelper(context)

        updateRecyclerView(recyclerView)

        var btDelete = view?.findViewById<FloatingActionButton>(R.id.btdeletePayAccounts)

        btDelete?.setOnClickListener {
                PopUpAreUSure(
                    onSubmitClickListener = {
                        Toast.makeText(activity, "Usted borró: ", Toast.LENGTH_SHORT).show()

                    }
                ).show(parentFragmentManager,"dialog")
            }



        }




    private fun updateRecyclerView(recyclerView: RecyclerView?) {
        val dataPayedBoards = db.getPayedBoardData()

        recyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            recyclerView?.layoutManager = GridLayoutManager(activity, 2)
            // set the custom adapter to the RecyclerView
            adapter = AccountsPayedAdapter(dataPayedBoards)
            recyclerView?.adapter = adapter

            adapter.setOnItemClickListener(object : AccountsPayedAdapter.onItemClickLister {
                override fun onItemClick(id: Int) {
                    Toast.makeText(activity, "You clicked on item no. ${id}", Toast.LENGTH_SHORT)
                        .show()
                    val newActivity = Intent(activity, AccountView::class.java)
                    newActivity.putExtra("boardId", id)
                    startActivity(newActivity)
                }

            })

            adapter.setOnBtDeleteClickListener(object : AccountsPayedAdapter.onBtClickLister {
                override fun onBtClick(id: Int) {
                    Toast.makeText(activity, "You delete item no. ${id}", Toast.LENGTH_SHORT)
                        .show()
                    db.deletePayedBoard(id)

                    val newActivity = Intent(activity, AaccountsPayed::class.java)

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
         * @return A new instance of fragment PayedAccounts.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PayedAccounts().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}